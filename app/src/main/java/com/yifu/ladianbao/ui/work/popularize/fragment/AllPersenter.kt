package com.yifu.ladianbao.ui.work.popularize.fragment

import android.content.Context
import com.yifu.ladianbao.base.BaseKPresenter
import com.yifu.ladianbao.base.BasePresent
import com.yifu.ladianbao.base.BaseView

class AllPersenter(context: Context):BaseKPresenter<AllContract.View>(context), AllContract.Presenter{
    override fun AllList(status: String, p: Int) {
        var list= arrayListOf<AllItemBean>()
        for(i in 0 until 8){
            var bean = AllItemBean()
            list.add(bean)
        }
        mRootView?.onAlllistSuccess(list)

    }




}