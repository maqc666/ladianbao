package com.yifu.ladianbao.ui.finance.vip

import android.content.Context
import com.yifu.ladianbao.base.BaseKPresenter

class VIPPresenter(context: Context)  : BaseKPresenter<VIPContract.View>(context), VIPContract.Persenter {
    override fun getDataList(p: Int) {
        var list= arrayListOf<VIPBean>()
        for(i in 0 until 5){
            var bean = VIPBean()
            list.add(bean)
        }
        mRootView?.onDataSuccess(list)
    }
}