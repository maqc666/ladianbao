package com.yifu.ladianbao.ui.systemmanage.partner

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter

interface PartnerContract {

    interface View : IBaseView{
        fun onDataSuccess(list: List<PartnerBean>)
    }
    interface Persenter: IPresenter<View>{
        fun getDataList(p: Int)
    }
}