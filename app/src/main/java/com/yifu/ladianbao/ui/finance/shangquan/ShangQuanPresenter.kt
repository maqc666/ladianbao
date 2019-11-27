package com.yifu.ladianbao.ui.finance.shangquan

import android.content.Context
import com.yifu.ladianbao.base.BaseKPresenter

class ShangQuanPresenter (context: Context)  : BaseKPresenter<ShangQuanContract.View>(context), ShangQuanContract.Persenter {
    override fun getDataList(p: Int) {
        var list= arrayListOf<ShangQuanBean>()
        for(i in 0 until 5){
            var bean = ShangQuanBean()
            list.add(bean)
        }
        mRootView?.onDataSuccess(list)
    }
}