package com.yifu.ladianbao.ui.finance.vip

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter
import com.yifu.ladianbao.ui.finance.business.BusinessBean
import com.yifu.ladianbao.ui.finance.business.BusinessListBean
import com.yifu.ladianbao.ui.finance.shangquan.ShangQuanBean

interface VIPContract {

    interface View : IBaseView {
        fun onDataSuccess(bean: VIPBean)
        fun onDetailSuccess(bean: BusinessListBean)
        fun onDataFail(msg:String)
    }
    interface Persenter: IPresenter<View> {
        fun getDataList(p: Int,token: String)
        fun getDetailList(p: Int,token: String)
    }
}