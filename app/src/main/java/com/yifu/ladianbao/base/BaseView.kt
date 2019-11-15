package com.yifu.ladianbao.base

import android.content.Context

interface BaseView {
    fun getContext(): Context
    fun onEmpty()

    fun onError()
}
