package com.yifu.ladianbao.ui.finance.shangquan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.finance.business.BusinessAdapter
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*


class ShangQuanActivity : BaseKActivity(),ShangQuanContract.View {
    override fun onDataSuccess(list: List<ShangQuanBean>) {
        dataList.addAll(list)
        adapter?.setNewData(dataList)
    }

    override fun onFail(msg: String) {
        showToast(msg) }

    override fun layoutId(): Int {
        return R.layout.activity_business
    }

    override fun initView() {
        tv_title.text = "商圈收益"
        image_back.setOnClickListener {
            finish()
        }
       

        mPresenter.attachView(this)
        rv_business.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        adapter = ShangQuanAdapter()
        adapter?.bindToRecyclerView(rv_business)
        }

    override fun initData() {
        mPresenter.getDataList(page)
         }
    var page = 1
    var adapter: ShangQuanAdapter? = null
    val dataList = arrayListOf<ShangQuanBean>()
    val mPresenter by lazy { ShangQuanPresenter(this) }
    var typeId = 0
    var index = 1

}
