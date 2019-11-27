package com.yifu.ladianbao.ui.systemmanage.syslist.all

import android.content.Context
import com.yifu.ladianbao.base.BaseKPresenter

class AllPresenter(context: Context) : BaseKPresenter<AllContact.View>(context),AllContact.Presenter {
    override fun getDataList(p: Int) {
        var list= arrayListOf<ContentBean>()
        for(i in 0 until 8){
            var bean = ContentBean()
            list.add(bean)
        }
        mRootView?.onDataListSuccess(list)
    }
}