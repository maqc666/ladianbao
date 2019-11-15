package com.yifu.ladianbao.wedget;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.yifu.ladianbao.R;

public class CustomProgressDialog extends Dialog {
    public CustomProgressDialog(Context context) {
        super(context, R.style.CustomProgressDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_custom_dialog);
    }


}

