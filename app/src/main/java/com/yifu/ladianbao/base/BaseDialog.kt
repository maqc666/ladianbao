package com.yifu.inyouquan.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle

import com.yifu.ladianbao.R



abstract class BaseDialog(mContext: Context) : Dialog(mContext, R.style.custom_dialog) {

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        initView()
    }

    private fun initView() {
        initLogic()
        setListener()
        processLogic()
    }


    protected abstract fun initLogic()
    protected abstract fun setListener()
    protected abstract fun processLogic()
}