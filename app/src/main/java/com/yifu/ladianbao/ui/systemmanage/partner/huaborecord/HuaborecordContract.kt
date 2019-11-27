package com.yifu.ladianbao.ui.systemmanage.partner.huaborecord

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter

interface HuaborecordContract  {
    interface View : IBaseView {
        fun onDataSuccess(list: List<HuaboBean>)
    }
    interface Persenter: IPresenter<View> {
        fun getDataList(p: Int)
    }
}