package com.yifu.ladianbao.ui.systemmanage.order.sysorder

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter

interface SysOrderContract {

    interface View : IBaseView {
        fun onDataSuccess(list: List<SysOrderBean>)
    }
    interface Persenter: IPresenter<View> {
        fun getDataList(p: Int)
    }
}