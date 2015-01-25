package com.syrine;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by syrinetrabelsi on 25/01/15.
 */
public class WordPressWebview extends Activity {
    WebView webview;
    private Intent mIntent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webview = new WebView(this);
        setContentView(webview);
        if (getIntent() != null) {
            String content = getIntent().getExtras().getString("content");
            String mime = "text/html";
            String encoding = "utf-8";

            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadDataWithBaseURL(null, content, mime, encoding, null);

            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.contains("code=")) {
                        Uri uri = Uri.parse(url);
                        Log.i("url=> ", url);
                        String code = uri.getQueryParameter("code");
                        Intent i = new Intent();
                        i.putExtra("code", code);
                        setResult(RESULT_OK, i);

                        finish();

                        return true;
                    } else {
                        webview.loadUrl(url);
                    }
                    return false;
                }


            });
        }
    }
}
