package app.mailjunky.de;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

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
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl("https://m.mailjunky.de/");

// Stop local links and redirects from opening in browser instead of WebView
        mWebView.setWebViewClient(new MyAppWebViewClient());
        mWebView.requestFocus(View.FOCUS_DOWN);
        mWebView.setVisibility(View.VISIBLE);

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
//Launch change log dialog

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Mailjunky APP Schließen!")
                    .setMessage("Bist du dir sicher dass du die APP Schließen willst?")
                    .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("Nein", null)
                    .show();
        }
    }
}
