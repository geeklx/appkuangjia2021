package com.haier.cellarette.libwebview.base;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.geek.libutils.SlbLoginUtil;
import com.geek.libutils.app.MyLogUtil;
import com.haier.cellarette.libwebview.R;
import com.haier.cellarette.libwebview.hois2.HiosHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String UA = "Mozilla/5.0 (Linux; Android 5.1; sudy6580_we_l Build/C320) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Safari/537.36";

    private View mBackImageView;
    private View mBackView;
    private View mCloseView;
    private TextView mTitleTextView;
    private ProgressBar mProgressBar;

    protected WebView mWebView;

    private String mUrl;
    private String AppToken;
    private LinearLayout mTop;
    protected ViewGroup parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_webview_layout);
        setUp();
        mUrl = getIntent().getStringExtra("url");
        AppToken = getIntent().getStringExtra("AppToken");

        loadUrl(mUrl);
    }

    protected void setUp() {
        findview();
        onclickListener();
        setupWebView();
    }

    /**
     * ture  ??????
     * falas  ?????????
     *
     * @param b
     */
    protected void setTopVisible(boolean b) {
        if (b) {
            mTop.setVisibility(View.VISIBLE);
        } else {
            mTop.setVisibility(View.GONE);
        }
    }


    /**
     * ture  ??????
     * falas  ?????????
     *
     * @param b
     */
    protected void setProgress(boolean b) {
        if (b) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    protected void findview() {
        mTop = findViewById(R.id.rlTop);
        mBackImageView = findViewById(R.id.ic_back);
        mBackView = findViewById(R.id.back);
        mCloseView = findViewById(R.id.close);
        mTitleTextView = findViewById(R.id.title);
        mProgressBar = findViewById(R.id.progress);
        mCloseView.setVisibility(View.VISIBLE);
    }

    protected void onclickListener() {
        mBackImageView.setOnClickListener(this);
        mBackView.setOnClickListener(this);
        mCloseView.setOnClickListener(this);
    }

    protected void setupWebView() {
        mWebView = new WebView(getApplicationContext());
        mWebView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        parent = findViewById(R.id.container);
        parent.addView(mWebView);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWebView.setBackgroundColor(0); // ???????????????
        WebSettings settings = mWebView.getSettings();//??????webbiew???settings
        // ??????(storage)
        // ??????HTML5 DOM storage API???????????? false
        settings.setDomStorageEnabled(true);
        // ??????Web SQL Database API????????????????????????????????????????????????WebView???????????? false
        // ???API??????????????????
        settings.setDatabaseEnabled(true);
        // ??????(location)
        settings.setGeolocationEnabled(true);
        // ????????????????????????
        settings.setSaveFormData(true);
        // ?????????webview??????requestFocus??????????????????????????????????????????????????? true
        settings.setNeedInitialFocus(true);
        // ????????????viewport?????????????????? false
        // ????????????`<meta name="viewport" ... />`?????????????????????
        settings.setUseWideViewPort(true);
        // ????????????overview mode???????????????????????? false
        // ?????????????????????WebView???????????????????????????????????????WebView??????
        settings.setLoadWithOverviewMode(true);
        // ????????????
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        // ????????????Javascript????????????false
        settings.setJavaScriptEnabled(true);
        // ?????????????????????????????????false
        settings.setSupportMultipleWindows(false);
        // ????????????Javascript(window.open)???????????????????????? false
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // ????????????
        settings.setAllowContentAccess(true); // ???????????????Content Provider????????????????????? true
        settings.setAllowFileAccess(true);    // ??????????????????????????????????????? true
        // ????????????
        settings.setLoadsImagesAutomatically(true); // ????????????????????????
        settings.setBlockNetworkImage(false);       // ????????????????????????
        settings.setBlockNetworkLoads(false);       // ??????????????????????????????
        // ??????(zoom)
        settings.setSupportZoom(true);          // ??????????????????
        settings.setBuiltInZoomControls(true); // ??????????????????????????????
        settings.setDisplayZoomControls(false);  // ??????????????????????????????
        // ?????????????????????????????? "UTF-8"
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setDefaultFontSize(16);        // ??????????????????????????????16???????????????1-72
        settings.setDefaultFixedFontSize(16);   // ????????????????????????????????????16
        settings.setMinimumFontSize(8);         // ?????????????????????????????? 8
        settings.setMinimumLogicalFontSize(8);  // ???????????????????????????????????? 8
        settings.setTextZoom(100);              // ????????????????????????????????? 100
        settings.setUserAgentString(UA + "; android");//slb-android
        settings.setMediaPlaybackRequiresUserGesture(false);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.addJavascriptInterface(this, "android");//js??????tag

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("---webviewurl--", url);
                if (url.contains("hios://NaviBack")) {
//                    onBackPressed();
                    finish();
                    return true;
                }
                if (url.startsWith("mailto:")) {
                    //Handle mail Urls
                    startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(url)));
                    return true;
                }
                if (url.startsWith("tel:")) {
                    //Handle telephony Urls
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(url)));
                    return true;
                } /*else {
                    view.loadUrl(url);
                }*/
                return HiosHelper.shouldOverrideUrl(WebViewActivity.this, getUrl(url));
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // ???????????????ssl????????? ??????????????? ????????????
                Log.d("geek11111111111111", error.toString());
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String title = view.getTitle();
                if (title != null) {
                    mTitleTextView.setText(title);
                }
                super.onPageFinished(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitleTextView.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() == View.GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
    }

    private String getUrl(String url) {
        String aaaa = "";
        try {
            aaaa = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return aaaa;
    }

    protected void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    protected void loadUrl2(String div) {
        // ?????????
        mWebView.loadDataWithBaseURL(null, getNewContent(div), "text/html", "UTF-8", null);
    }

    private String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            if (element.className() != null && element.className().length() > 0)
                element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mWebView.loadUrl("javascript:onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.loadUrl("javascript:onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.loadUrl("javascript:onPause()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        mWebView.loadUrl("javascript:onStop()");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_back || id == R.id.back) {
            onBackPressed();
            return;
        }

        if (id == R.id.close) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
//        FixInputMethodBug.fixFocusedViewLeak(getApplication());
        mWebView.loadUrl("javascript:onDestroy()");

        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.clearCache(true);
            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        //
        if (ActivityUtils.getActivityList().size() == 1) {
            startActivity(new Intent(AppUtils.getAppPackageName() + ".hs.act.slbapp.ShouyeActivity"));
        }
        super.onDestroy();
    }

    protected void set_destory() {
        mWebView.loadUrl("javascript:onDestroy()");

        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.clearCache(true);
            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    // *****js??????????????????****

    private String user_id = "aaaa";

    /**
     * ????????????userId,????????????js??????onGetUserId?????????user_id
     */
    @JavascriptInterface
    public void userId() {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                // ?????????????????? Android 4.4 ?????????????????????????????????????????????????????????
                if (Build.VERSION.SDK_INT < 18) {
                    mWebView.loadUrl("javascript:actionFromNativeWithParam(\"" + user_id + "\")");
                } else {
                    mWebView.evaluateJavascript("javascript:actionFromNativeWithParam(\"" + user_id + "\")", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //????????? js ???????????????
                            MyLogUtil.e("webview", value);
                        }
                    });
                }
            }
        });
//        mWebView.post(new Runnable() {
//            @Override
//            public void run() {
////                loginToDo(new Runnable() {
////                    @Override
////                    public void run() {
////                        mWebView.loadUrl("javascript:onGetUserId(\"" + user_id + "\")");
////                    }
////                });
//                mWebView.loadUrl("javascript:onGetUserId(\"" + user_id + "\")");
//            }
//        });
    }

    /**
     * type 0: ????????????????????? 1 ??????
     */
    @JavascriptInterface
    public void layerType(int type) {
        if (type == 0) {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            return;
        }

        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @JavascriptInterface
    public String getVersion(final String content) {
        return AppUtils.getAppVersionName(AppUtils.getAppPackageName());
    }


    private String mode = "";

    /**
     * ??????fridgeModel
     *
     * @return
     */
    @JavascriptInterface
    public String fridgeModel() {
        return mode;
    }


    @JavascriptInterface
    public void finishPage() {
        finish();
    }

    /**
     * hios://com.example.shining.p022_hios20.activity.NoHiosMainActivity?act={i}1&sku_id={s}2a&category_id={s}3a
     *
     * @param hios_url
     */
    @JavascriptInterface
    public void JumpToShop(String hios_url) {
        HiosHelper.resolveAd(WebViewActivity.this, WebViewActivity.this, hios_url);
    }

//    private void loginToDo(Runnable r) {
//        UserUtils.loginToDo(this, r);
//    }

    @JavascriptInterface
    public String getAppToken() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("token", AppToken);
        final String callbackData = obj.toString();
        LogUtils.e("ssssssssssss", callbackData);
        return callbackData;
    }


    /**
     * js???????????????
     */
    @JavascriptInterface
    public void actionFromJs() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebViewActivity.this, "??????????????????~", Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * js???????????????. ???????????????????????????
     *
     * @param str js  ?????????????????????
     */
    @JavascriptInterface
    public void actionFromJsWithParam(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebViewActivity.this, "??????????????????????????????????????????~" + str, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (SlbLoginUtil.get().login_activity_result(requestCode, resultCode, data)) {
            return;
        }
        //????????????
        onActResult(requestCode, resultCode, data);
    }

    protected void onUserLogined(String userId) {
    }

    protected void onActResult(int requestCode, int resultCode, Intent data) {
    }

}
