package com.yifu.ladianbao.ui.systemmanage.order.sysorder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import kotlinx.android.synthetic.main.activity_sys_order_activty.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class SysOrderActivty : BaseKActivity(),SysOrderContract.View{
    override fun onDataSuccess(list: List<SysOrderBean>) {
        dataList.addAll(list)
        adapter?.setNewData(dataList)
    }

    override fun onFail(msg: String) {
        showToast(msg) }

    override fun layoutId(): Int {
        return R.layout.activity_sys_order_activty
    }

    override fun initView() {
        tv_title.text="订购记录";
        image_back.setOnClickListener{
            finish()
        }

        mPresenter.attachView(this)
        rv_sys_order.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        adapter = SysOrderAdapter()
        adapter?.bindToRecyclerView(rv_sys_order)

    }

    override fun initData() {

        mPresenter.getDataList(page)
         }
    var page = 1
    var adapter: SysOrderAdapter? = null
    val dataList = arrayListOf<SysOrderBean>()
    val mPresenter by lazy { SysOrderPresenter(this) }
    var typeId = 0
    var index = 1

}
