package com.yifu.ladianbao.base

import android.content.Context
import com.lzy.okgo.OkGo

open class BaseKPresenter<V: IBaseView> (context: Context)  : IPresenter<V> {

    var mRootView: V? = null
        private set

    val mContext: Context = context

    override fun attachView(mRootView: V) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        OkGo.getInstance().cancelTag(mRootView)
        mRootView = null
    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")
}