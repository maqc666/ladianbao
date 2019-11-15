package com.yifu.inyouquan.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle

import com.yifu.ladianbao.R


/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author         与天同行的观测者
 * @Copyright      Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date           $date$ $time$
 */
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