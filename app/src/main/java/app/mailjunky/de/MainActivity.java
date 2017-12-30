package app.mailjunky.de;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Layout of the main activity
        setContentView(R.layout.activity_main);

        // Show the "What's New" screen once for each new release of the application
        new WhatsNewScreen(this).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webView);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.clearCache(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl("https://m.mailjunky.de/");

// Stop local links and redirects from opening in browser instead of WebView
        mWebView.setWebViewClient(new MyAppWebViewClient());
//Launch change log dialog

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                        mWebView.reload();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
}
}
