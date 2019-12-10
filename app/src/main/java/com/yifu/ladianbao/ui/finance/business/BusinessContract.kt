package com.yifu.ladianbao.ui.finance.business

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter
import com.yifu.ladianbao.ui.systemmanage.partner.PartnerBean

interface BusinessContract {

    interface View : IBaseView {

        fun onSuccess(bean: BusinessBean)
        fun onDataFail(msg:String)
    }
    interface Persenter: IPresenter<View> {
        fun getDataList(token: String)


    }
}