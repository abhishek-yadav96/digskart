package com.example.digskart.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digskart.R;
import com.example.digskart.Utils.Utils;


public class WebViewActivity extends AppCompatActivity {

    ImageView backButton;
    public TextView topbartext, copyright;
    WebView web_view;
    LinearLayout networkerror;
    public static AlertDialog.Builder builder;
    public static AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.webview_activity);
        backButton = (ImageView) findViewById(R.id.backButton);
        topbartext = (TextView) findViewById(R.id.topbartext);
        web_view = (WebView) findViewById(R.id.web_view);
        networkerror = (LinearLayout) findViewById(R.id.networkerror);
        copyright = (TextView) findViewById(R.id.copyright);
        topbartext.setShadowLayer(25, 0, 0, Color.WHITE);
        String copy = Utils.copyrights();
        copyright.setText(Html.fromHtml(copy), TextView.BufferType.SPANNABLE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(Utils.isNetworkAvailable(WebViewActivity.this)){
            builder = new AlertDialog.Builder(WebViewActivity.this);
            LayoutInflater inflater = (LayoutInflater) WebViewActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.progress_dialog,(ViewGroup) findViewById(R.id.layout_root));
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Rendering...");
            builder.setView(layout);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();
        }
        web_view.setBackgroundColor(0x00000000);
        web_view.clearCache(true);
        web_view.clearHistory();
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        web_view.setVerticalScrollBarEnabled(false);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        settings.setEnableSmoothTransition(true);
        web_view.requestFocus();
        settings.setDefaultTextEncodingName("utf-8");
        Intent intent = getIntent();
        if(intent!=null){
            String TEXT = intent.getStringExtra("TEXT");
            if(TEXT.equalsIgnoreCase("privacy_policy")){
                topbartext.setText("Privacy Policy");
                if(Utils.isNetworkAvailable(WebViewActivity.this)){
                    web_view.setVisibility(View.VISIBLE);
                    networkerror.setVisibility(View.GONE);
                    web_view.loadUrl("https://www.digskart.com/privacy-policy/");
                }else{
                    web_view.setVisibility(View.GONE);
                    networkerror.setVisibility(View.VISIBLE);
                }
            }else if(TEXT.equalsIgnoreCase("terms_and_conditions")){
                topbartext.setText("Terms & Conditions");
                if(Utils.isNetworkAvailable(WebViewActivity.this)){
                    web_view.setVisibility(View.VISIBLE);
                    networkerror.setVisibility(View.GONE);
                    web_view.loadUrl("https://www.digskart.com/terms/");
                }else{
                    web_view.setVisibility(View.GONE);
                    networkerror.setVisibility(View.VISIBLE);
                }
            }else if(TEXT.equalsIgnoreCase("Refund_policy")){
                topbartext.setText("Refund policy");
                if(Utils.isNetworkAvailable(WebViewActivity.this)){
                    web_view.setVisibility(View.VISIBLE);
                    networkerror.setVisibility(View.GONE);
                    web_view.loadUrl("https://www.digskart.com/refund-policy/");
                }else{
                    web_view.setVisibility(View.GONE);
                    networkerror.setVisibility(View.VISIBLE);
                }
            }else if(TEXT.equalsIgnoreCase("Rate_us_star")){
                topbartext.setText("Rate Us Five Star");
                if(Utils.isNetworkAvailable(WebViewActivity.this)){
                    web_view.setVisibility(View.VISIBLE);
                    networkerror.setVisibility(View.GONE);
                    web_view.loadUrl("https://play.google.com/store/apps/details?id=com.digskart.customer");
                }else{
                    web_view.setVisibility(View.GONE);
                    networkerror.setVisibility(View.VISIBLE);
                }
            }else{
                topbartext.setText("About Us");
                if(Utils.isNetworkAvailable(WebViewActivity.this)){
                    web_view.setVisibility(View.VISIBLE);
                    networkerror.setVisibility(View.GONE);
                    web_view.loadUrl("https://www.digskart.com/about/");
                }else{
                    web_view.setVisibility(View.GONE);
                    networkerror.setVisibility(View.VISIBLE);
                }
                settings.setSupportZoom(true);
                settings.setBuiltInZoomControls(true);
                settings.setDisplayZoomControls(false);
            }
        }
        web_view.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                alertDialog.dismiss();
            }
        });
    }
}
