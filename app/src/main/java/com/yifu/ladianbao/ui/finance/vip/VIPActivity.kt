package com.yifu.ladianbao.ui.finance.vip

import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.finance.business.BusinessAdapter
import com.yifu.ladianbao.ui.finance.business.BusinessListBean
import com.yifu.ladianbao.ui.finance.shangquan.ShangQuanBean
import com.yifu.ladianbao.util.LoginUtils
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class VIPActivity : BaseKActivity(),VIPContract.View {
    override fun onDataSuccess(bean: VIPBean) {
        tv_money.text=bean.bonus
        tv_commission.text=bean.bonus
        tv_yesterday.text=bean.money_yesterday
    }

    override fun onDetailSuccess(bean: BusinessListBean) {
//        dataList.addAll(bean.data)
//        adapter?.setNewData(dataList)

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
//        if (dataList.size <= 0) {
//            ll_empty.visibility = View.VISIBLE
//        } else {
//            ll_empty.visibility = View.GONE
//        }

    }

    override fun onDataFail(msg: String) {
        refresh_layout.finishRefresh()
        showToast(msg)
        }



    override fun onFail(msg: String) {
        refresh_layout.finishRefresh()
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
        adapter = BusinessAdapter()
        adapter?.bindToRecyclerView(rv_business)
        }

    override fun initData() {
        mPresenter.getDataList(page,LoginUtils.getToken())
        mPresenter.getDetailList(page,LoginUtils.getToken())
           }
    var page = 1
    var adapter: BusinessAdapter? = null
    val dataList = arrayListOf<BusinessListBean.DataBean>()
    val mPresenter by lazy { VIPPresenter(this) }
    var typeId = 0
    var index = 1

}
