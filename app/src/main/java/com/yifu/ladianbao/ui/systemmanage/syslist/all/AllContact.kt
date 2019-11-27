package com.yifu.ladianbao.ui.systemmanage.syslist.all

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter

interface AllContact {

    interface View: IBaseView{
        fun onDataListSuccess(list: List<ContentBean>)
    }
    interface Presenter: IPresenter<View>{
    fun getDataList(p: Int)}
}