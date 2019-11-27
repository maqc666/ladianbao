package com.yifu.ladianbao.ui.finance.business

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.systemmanage.partner.PartnerPresenter
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class BusinessActivity : BaseKActivity(),BusinessContract.View {
    override fun onDataSuccess(list: List<BusinessBean>) {
        dataList.addAll(list)
        adapter?.setNewData(dataList)
    }

    override fun onFail(msg: String) {
        showToast(msg)  }

    override fun layoutId(): Int {
        return R.layout.activity_business
    }

    override fun initView() {
        tv_title.text = "交易分润"
        image_back.setOnClickListener {
            finish()
        }
        ll_title.visibility= View.VISIBLE


        mPresenter.attachView(this)
        rv_business.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        adapter = BusinessAdapter()
        adapter?.bindToRecyclerView(rv_business)


    }
    override fun initData() {
        mPresenter.getDataList(page)
         }
    var page = 1
    var adapter: BusinessAdapter? = null
    val dataList = arrayListOf<BusinessBean>()
    val mPresenter by lazy { BusinessPresenter(this) }
    var typeId = 0
    var index = 1

}