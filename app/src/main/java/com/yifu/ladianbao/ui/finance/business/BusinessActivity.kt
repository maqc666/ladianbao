package com.yifu.ladianbao.ui.finance.business

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.util.LoginUtils
import com.yifu.ladianbao.util.utilcode.ToastUtils
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class BusinessActivity : BaseKActivity(),BusinessContract.View {
    override fun onCashSuccess(bean: BusinessListBean) {
        if (page == 1) {
            dataList.clear()
            refresh_layout.finishRefresh()
        }
        if (bean.data.size < 10) {
            refresh_layout.finishLoadMoreWithNoMoreData()
        }
        dataList.addAll(bean.data)
        refresh_layout.finishLoadMore()
        cashAdapter?.setNewData(dataList)
        if (dataList.size <= 0) {
            ll_empty.visibility = View.VISIBLE
        } else {
            ll_empty.visibility = View.GONE
        }

    }

    var state=1
    override fun onSuccess(bean: BusinessBean) {
        tv_money.text=bean.money
        tv_commission.text=bean.commission
        tv_yesterday.text=bean.commission_yesterday
    }

    override fun onDataFail(msg: String) {
        showToast(msg)
    }

    override fun onDataSuccess(bean:BusinessListBean) {
        if (page == 1) {
            dataList.clear()
            refresh_layout.finishRefresh()
        }
        if (bean.data.size < 10) {
            refresh_layout.finishLoadMoreWithNoMoreData()
        }
        dataList.addAll(bean.data)
        refresh_layout.finishLoadMore()
        adapter?.setNewData(dataList)
        if (dataList.size <= 0) {
            ll_empty.visibility = View.VISIBLE
        } else {
            ll_empty.visibility = View.GONE
        }
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

        ll_cash.setOnClickListener {
            rv_business.visibility=View.GONE
            rv_cash.visibility=View.VISIBLE
            showToast("cash")
        }



        mPresenter.attachView(this)
        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                initData()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                initData()
            }
        })
        rv_business.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rv_cash.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        adapter = BusinessAdapter()
        adapter?.bindToRecyclerView(rv_business)
        cashAdapter= ShangQuanAdapter()
        cashAdapter?.bindToRecyclerView(rv_cash)

//




    }
    override fun initData() {
        mPresenter.getDataList(LoginUtils.getToken())
        mPresenter.getIncomeList(LoginUtils.getToken(),page)
        mPresenter.getCashList(LoginUtils.getToken(),page)

         }
    var page = 1
    var adapter: BusinessAdapter? = null
    var cashAdapter:ShangQuanAdapter?=null
    val dataList = arrayListOf<BusinessListBean.DataBean>()
    val mPresenter by lazy { BusinessPresenter(this) }
    var typeId = 0
    var index = 1

}
