package com.yifu.ladianbao.ui.systemmanage.partner

import android.content.Context
import com.yifu.ladianbao.base.BaseKPresenter
import com.yifu.ladianbao.ui.systemmanage.syslist.all.AllContact
import com.yifu.ladianbao.ui.systemmanage.syslist.all.ContentBean

class PartnerPresenter(context: Context)  : BaseKPresenter<PartnerContract.View>(context), PartnerContract.Persenter {
    override fun getDataList(p: Int) {
        var list= arrayListOf<PartnerBean>()
        for(i in 0 until 8){
            var bean = PartnerBean()
            list.add(bean)
        }
        mRootView?.onDataSuccess(list)
    }
}