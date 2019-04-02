package com.apps.harsh.zest.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.apps.harsh.zest.ConnectionDetector;
import com.apps.harsh.zest.R;
import com.wang.avi.AVLoadingIndicatorView;

public class WebFragment extends AppCompatActivity {
    ConnectionDetector connectionDetector;
    AVLoadingIndicatorView avLoadingIndicatorView;
    AlertDialog alertDialog;
    public WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_offers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        avLoadingIndicatorView = findViewById(R.id.avi);
        avLoadingIndicatorView.show();
        connectionDetector = new ConnectionDetector(this);
        if (connectionDetector.isConnected()) {
            mWebView = findViewById(R.id.webView);
            mWebView.loadUrl("https://www.coepzest.org");

            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.setWebViewClient(new WebViewClient());
            avLoadingIndicatorView.hide();
        }else {
            alertDialog.setTitle("No Internet");
            alertDialog.setMessage("Please Connect to Network...!");
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                            startActivity(new Intent(Settings.ACTION_SETTINGS));
                        }
                    });
            alertDialog.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
