package com.yifu.ladianbao.ui.systemmanage.partner.huaborecord

import android.content.Context
import com.yifu.ladianbao.base.BaseKPresenter

class HuaboPresenter (context: Context)  : BaseKPresenter<HuaborecordContract.View>(context), HuaborecordContract.Persenter {
    override fun getDataList(p: Int) {
        var list= arrayListOf<HuaboBean>()
        for(i in 0 until 8){
            var bean = HuaboBean()
            list.add(bean)
        }
        mRootView?.onDataSuccess(list)
    }}