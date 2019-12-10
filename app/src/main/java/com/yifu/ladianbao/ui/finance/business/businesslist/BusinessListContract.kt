package com.yifu.ladianbao.ui.finance.business.businesslist

import com.yifu.ladianbao.base.BasePresent
import com.yifu.ladianbao.base.BaseView
import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter
import com.yifu.ladianbao.ui.finance.business.BusinessBean
import com.yifu.ladianbao.ui.finance.business.BusinessListBean

interface BusinessListContract {

    interface View : BaseView {
        fun onDataSuccess(bean: BusinessListBean)
        fun onDataFail(msg:String)

    }

    abstract class Present : BasePresent<View>() {
        abstract fun getDataList(token: String)
      //  abstract fun getCashList(token: String,p: Int)
        abstract fun getIncomeList(token: String,p: Int,income: Int)

    }
}