package app.mailjunky.de;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

/**
 * Created by Christoph on 04.06.2016.
 */
public class MyAppWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if ("m.mailjunky.de".endsWith(Objects.requireNonNull(Uri.parse(url).getHost()))) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);

        return true;

    }

}
