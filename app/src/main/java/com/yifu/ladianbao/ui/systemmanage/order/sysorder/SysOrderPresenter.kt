package com.yifu.ladianbao.ui.systemmanage.order.sysorder

import android.content.Context
import com.yifu.ladianbao.base.BaseKPresenter
import com.yifu.ladianbao.ui.systemmanage.partner.huaborecord.HuaboBean
import com.yifu.ladianbao.ui.systemmanage.partner.huaborecord.HuaborecordContract

class SysOrderPresenter (context: Context)  : BaseKPresenter<SysOrderContract.View>(context), SysOrderContract.Persenter {
    override fun getDataList(p: Int) {
        var list= arrayListOf<SysOrderBean>()
        for(i in 0 until 8){
            var bean = SysOrderBean()
            list.add(bean)
        }
        mRootView?.onDataSuccess(list)
    }}