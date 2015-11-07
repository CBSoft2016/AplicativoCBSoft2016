package com.applications.fronchetti.cbsoft2016.Atividades;

import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.applications.fronchetti.cbsoft2016.R;

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.google.com.br");
    }

}
