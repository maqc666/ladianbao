package com.yifu.ladianbao.ui.work.popularize.fragment

import com.yifu.ladianbao.base.BasePresent
import com.yifu.ladianbao.base.BaseView
import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter
import com.yifu.ladianbao.ui.systemmanage.order.sysorder.SysOrderBean

interface AllContract {

    interface View : IBaseView {
        fun onAlllistSuccess(list: List<AllItemBean>)
//        fun onSetStatusSuccess(info:String)
//        fun onDelSuccess(info:String)
    }

   interface Presenter : IPresenter<View>  {
        fun AllList(status:String,p:Int)
//        abstract fun setStatus(id:String,status:String)
//        abstract fun del(id:String)
    }
}