package com.example.digskart.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.digskart.R;
import com.example.digskart.Utils.Settings;
import com.example.digskart.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UploadViedo extends AppCompatActivity {

    Button btn1,submit;
    VideoView videoshow;
    private int GALLERY = 1, CAMERA = 2;
    private static final String VIDEO_DIRECTORY = "/lnitv";
    Uri contentURI;
    File photoFile;
    String spath;
    String selectedVideoPath;
    private RequestQueue rQueue;
    Bitmap bitmap;
    ImageView action_image;
    public static LinearLayout progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_upload);
        btn1 = findViewById(R.id.btn1);
        submit = findViewById(R.id.submit);
        progress = (LinearLayout) findViewById(R.id.progress);

        action_image = findViewById(R.id.action_image);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.getUserDataToken(UploadViedo.this);
                Utils.getUserData(UploadViedo.this);
                String TOKen = Settings.TOKEN;
                String USERID = Settings.USERID;
                String filename = getFileName(contentURI);
                creatingImageVideoPost(filename,contentURI,TOKen,USERID);
            }
        });
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select video from gallery",
                "Record video from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chooseVideoFromGallary();
                                break;
                            case 1:
                                takeVideoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void takeVideoFromCamera() {
        Intent captureVideoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(captureVideoIntent, 2);
    }

    private void chooseVideoFromGallary() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 3);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == UploadViedo.RESULT_CANCELED) {
            return;
        }
        if (resultCode == Activity.RESULT_OK) {
             if (requestCode == 2) {
                contentURI = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = UploadViedo.this.getContentResolver().query(contentURI, filePathColumn, null, null, null);
                cursor.moveToNext ();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                bitmap = ThumbnailUtils.createVideoThumbnail(picturePath, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
                action_image.setImageBitmap(bitmap);
            }else if (requestCode == 3) {
                contentURI = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = UploadViedo.this.getContentResolver().query(contentURI, filePathColumn, null, null, null);
                cursor.moveToNext ();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bitmap = ThumbnailUtils.createVideoThumbnail(picturePath, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
                action_image.setImageBitmap(bitmap);
            }
        }
    }

    @SuppressLint("Range")
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = UploadViedo.this.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToNext ()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[16*bufferSize];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private void creatingImageVideoPost(final String filename, Uri docfile, final String name, final String email){
        Log.e("DATA","DATA "+docfile);
        progress.setVisibility(View.VISIBLE);
        InputStream iStream = null;
        byte[] inputData = new byte[0];
        try {
            iStream = UploadViedo.this.getContentResolver().openInputStream(docfile);
            inputData = getBytes(iStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] finalInputData = inputData;
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, "https://api.digskart.com/api/UploadVideo?Orderid=2", new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                Log.e("RESPONSE", "RESPONSE>>> "+new String(response.data));
               progress.setVisibility(View.GONE);
                rQueue.getCache().clear();
                try {
                    JSONObject jsonObject = new JSONObject(new String(response.data));
                    Toast.makeText(getApplicationContext(),jsonObject.getString("Message"),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   progress.setVisibility(View.GONE);
                        Log.e("RESPONSE", "RESPONSE>>> "+error);
                        progress.setVisibility(View.GONE);
                    }
                } ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String >headers=new HashMap<String,String>();
                headers.put("Userid", email);
                headers.put("Token", name);
                Log.e("GEADER","HEADER"+headers);
                return headers;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                params.put("FileName", new DataPart(filename, finalInputData));
                return params;
            }
        };
        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rQueue = Volley.newRequestQueue(UploadViedo.this);
        rQueue.add(volleyMultipartRequest);
    }

}
