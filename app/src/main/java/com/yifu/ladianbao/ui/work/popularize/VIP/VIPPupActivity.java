package com.yifu.ladianbao.ui.work.popularize.VIP;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yifu.ladianbao.R;
import com.yifu.ladianbao.util.LoginUtils;

public class VIPPupActivity extends AppCompatActivity {

    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);

        web=findViewById(R.id.wv_vip);

        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        WebSettings setting=web.getSettings();
        setting.setJavaScriptEnabled(true);

       String id= LoginUtils.INSTANCE.getUserInfo().getAgent_id();


        web.loadUrl("http://ldb.yiqifuo.com/api/agent.help/inviteMember?id="+id);


    }

}
