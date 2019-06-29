package app.mailjunky.de;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private SwipeRefreshLayout layoutRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Layout of the main activity

        setContentView(R.layout.activity_main);

        // Show the "What's New" screen once for each new release of the application
        new WhatsNewScreen(this).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.webView);
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
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl("https://m.mailjunky.de/");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

// Stop local links and redirects from opening in browser instead of WebView
        mWebView.setWebViewClient(new MyAppWebViewClient());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        layoutRefresh = this.findViewById(R.id.swipeLayout);
        layoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mWebView.getUrl() != null) {
                    mWebView.reload(); // write what happens after swipe refreshing
                }
                layoutRefresh.setRefreshing(false); //this line hides the refresh button after completing
            }
        });

    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        mWebView.goBack();
        Toast.makeText(this, "Um die APP zu beenden drücke 'Zurück' erneut", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
