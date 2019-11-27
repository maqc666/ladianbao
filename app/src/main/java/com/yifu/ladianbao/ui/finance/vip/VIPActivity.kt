package com.yifu.ladianbao.ui.finance.vip

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.finance.shangquan.ShangQuanAdapter
import com.yifu.ladianbao.ui.finance.shangquan.ShangQuanBean
import com.yifu.ladianbao.ui.finance.shangquan.ShangQuanPresenter
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class VIPActivity : BaseKActivity(),VIPContract.View {
    override fun onDataSuccess(list: List<VIPBean>) {
        dataList.addAll(list)
        adapter?.setNewData(dataList)
    }

    override fun onFail(msg: String) {
        showToast(msg)
        }

    override fun layoutId(): Int {
        return R.layout.activity_business
    }

    override fun initView() {
        tv_title.text = "至尊卡收益"
        image_back.setOnClickListener {
            finish()
        }


        mPresenter.attachView(this)
        rv_business.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        adapter = VIPAdapter()
        adapter?.bindToRecyclerView(rv_business)
        }

    override fun initData() {
        mPresenter.getDataList(page)
           }
    var page = 1
    var adapter: VIPAdapter? = null
    val dataList = arrayListOf<VIPBean>()
    val mPresenter by lazy { VIPPresenter(this) }
    var typeId = 0
    var index = 1

}