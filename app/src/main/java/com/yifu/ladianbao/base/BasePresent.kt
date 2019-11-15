package com.yifu.ladianbao.base

import android.content.Context
import com.lzy.okgo.OkGo


abstract class BasePresent<V : BaseView> {
    var mView: V? = null
    var mContext: Context? = null
    open fun attachView(view: V) {
        mView = view
        mContext = mView?.getContext()
    }

    open fun detachView() {
        OkGo.getInstance().cancelTag(mView)
        mView = null
    }
}