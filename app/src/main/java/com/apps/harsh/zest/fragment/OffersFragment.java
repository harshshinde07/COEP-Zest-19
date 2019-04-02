package com.apps.harsh.zest.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.apps.harsh.zest.ConnectionDetector;
import com.apps.harsh.zest.R;
import com.wang.avi.AVLoadingIndicatorView;

public class OffersFragment extends Fragment {
    ConnectionDetector connectionDetector;
    AVLoadingIndicatorView avLoadingIndicatorView;
    AlertDialog alertDialog;
    public WebView mWebView;

    public static OffersFragment newInstance() {
        return new OffersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        avLoadingIndicatorView = view.findViewById(R.id.avi);
        avLoadingIndicatorView.show();
        connectionDetector = new ConnectionDetector(getActivity());
        if (connectionDetector.isConnected()) {
            mWebView = view.findViewById(R.id.webView);
            //TODO Replace with the given link
            mWebView.loadUrl("https://www.upto75.com/corporateCOEPZEST.html");

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
        return view;
    }

    public interface OnFragmentInteractionListener {
    }
}