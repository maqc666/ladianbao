package com.yifu.ladianbao.ui.work.popularize.businesspup

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter
import com.yifu.ladianbao.ui.login.UserBean
import com.yifu.ladianbao.ui.systemmanage.partner.PartnerBean

interface BusinessPupContract {

    interface View : IBaseView {
        fun onDataSuccess(bean: CircleBean)
        fun onDataFail(msg:String)
    }
    interface Persenter: IPresenter<View> {
        fun getDataList(token: String)
    }
}