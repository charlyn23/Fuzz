package charlyn23.c4q.nyc.fuzztest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by charlynbuchanan on 10/5/15.
 */
public class WebViewFragment extends Fragment {
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.webview, container, false);
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.webview);
//
//        webView = (WebView) findViewById(R.id.webView);
//        webView.setWebViewClient(new MyWebViewClient());
//
//        String url = "https://fuzzproductions.com";
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl(url);
//
//
//    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
