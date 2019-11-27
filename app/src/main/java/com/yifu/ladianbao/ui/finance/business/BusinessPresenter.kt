package com.yifu.ladianbao.ui.finance.business

import android.content.Context
import com.yifu.ladianbao.base.BaseKPresenter

class BusinessPresenter (context: Context)  : BaseKPresenter<BusinessContract.View>(context), BusinessContract.Persenter {
    override fun getDataList(p: Int) {
        var list= arrayListOf<BusinessBean>()
        for(i in 0 until 5){
            var bean = BusinessBean()
            list.add(bean)
        }
        mRootView?.onDataSuccess(list)
    }
}