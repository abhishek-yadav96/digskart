package com.example.digskart.Fragment;



import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digskart.R;
import com.example.digskart.Utils.Settings;
import com.example.digskart.Utils.Utils;

import java.net.URLEncoder;

public class Invitefriend extends Fragment {

    RelativeLayout copy;
    TextView text2;
    ImageView iamge1,refer,image3,share,back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_invite_friend, container, false);
        copy = view.findViewById(R.id.copy);
        text2 = view.findViewById(R.id.text2);
        iamge1 = view.findViewById(R.id.iamge1);
        refer = view.findViewById(R.id.refer);
        image3 = view.findViewById(R.id.image3);
        share = view.findViewById(R.id.share);
        back = view.findViewById(R.id.back);
        copy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                String label = text2.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(getActivity().CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", label);
                clipboard.setPrimaryClip(clip);
                if(clip != null){
                    Toast.makeText(getActivity(),"copyed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        iamge1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.getUserData(getActivity());
                String number = Settings.MOBILE;
                String code = text2.getText().toString();
                Log.e("NUMBER","NUMBER"+number);
                openWhatsApp(getActivity(),number,code);
            }
        });
        refer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callInstagram();
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFacebookPageURL(getActivity());
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = text2.getText().toString();
                if(!TextUtils.isEmpty(code)){
                    Intent shareIntent = new Intent();
                    shareIntent.setType("text/plain");
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, code);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, code);
                    startActivity(Intent.createChooser(shareIntent, "Share code"));
                }else {
                    Utils.showOKDialog(getActivity(),"NO Refer Code Found");
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment fragment2 = new ProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment2);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    private void openWhatsApp(final Activity activity, String code, String number) {
        try{
            PackageManager packageManager = activity.getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);
            String url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode(code, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                startActivity(i);
            }else {
                Toast.makeText(activity, getString(R.string.no_whatsapp), Toast.LENGTH_SHORT).show();
            }
        } catch(Exception e) {
            Log.e("ERROR WHATSAPP",e.toString());
            Toast.makeText(activity, getString(R.string.no_whatsapp), Toast.LENGTH_SHORT).show();
        }
    }

    private void callInstagram() {
        String apppackage = "com.instagram.android";
        try {
            Intent i = getActivity().getPackageManager().getLaunchIntentForPackage(apppackage);
            getActivity().startActivity(i);
        } catch (Exception  e) {
            Toast.makeText(getActivity(), "Sorry, Instagram Apps Not Found", Toast.LENGTH_LONG).show();
        }

    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "https://www.facebook.com/login/";
            } else { //older versions of fb app
                return "https://www.facebook.com/login/";
            }
        } catch (PackageManager.NameNotFoundException e) {
            return "https://www.facebook.com/login/"; //normal web url
        }
    }

}
