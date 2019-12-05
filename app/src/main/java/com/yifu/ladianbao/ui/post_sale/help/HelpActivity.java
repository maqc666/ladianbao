package com.yifu.ladianbao.ui.post_sale.help;


import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yifu.ladianbao.R;
import com.yifu.ladianbao.base.BaseKActivity;

public class HelpActivity extends BaseKActivity {

     WebView webview;
     TextView title;
     LinearLayout back;


    @Override
    public int layoutId() {
        return R.layout.activity_help;
    }

    @Override
    public void initView() {

        title=findViewById(R.id.tv_title);
        title.setText("帮助中心");
        back=findViewById(R.id.image_back);
        back.setOnClickListener(v -> {
               finish();
        });
        webview=findViewById(R.id.web_view);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
        WebSettings webSetting=webview.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webview.loadUrl("http://ldb.yiqifuo.com/api/agent.help/helpcenter");
    }

    @Override
    public void initData() {



    }
}

