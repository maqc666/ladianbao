package com.yifu.ladianbao.ui.finance.vip

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter
import com.yifu.ladianbao.ui.finance.shangquan.ShangQuanBean

interface VIPContract {

    interface View : IBaseView {
        fun onDataSuccess(list: List<VIPBean>)
    }
    interface Persenter: IPresenter<View> {
        fun getDataList(p: Int)
    }
}