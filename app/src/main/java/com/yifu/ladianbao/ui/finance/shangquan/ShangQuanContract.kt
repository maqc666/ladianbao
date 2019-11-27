package com.yifu.ladianbao.ui.finance.shangquan

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter

interface ShangQuanContract {

    interface View : IBaseView {
        fun onDataSuccess(list: List<ShangQuanBean>)
    }
    interface Persenter: IPresenter<View> {
        fun getDataList(p: Int)
    }
}