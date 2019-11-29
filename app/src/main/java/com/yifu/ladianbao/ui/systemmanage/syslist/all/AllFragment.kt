package com.yifu.ladianbao.ui.systemmanage.syslist.all


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKFragment
import com.yifu.ladianbao.ui.systemmanage.syslist.all.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_all.*

class AllFragment : BaseKFragment(),AllContact.View {
    override fun onDataListSuccess(list: List<ContentBean>) {
//        if (page == 1) {
//            dataList.clear()
//            refresh_layout.finishRefresh()
//        }
//        if (list.size < 5) {
//            refresh_layout.finishLoadMoreWithNoMoreData()
//        }
        dataList.addAll(list)
//        refresh_layout.finishLoadMore()
        adapter?.setNewData(dataList)
        if (dataList.size <= 0) {
            ll_empty.visibility = View.VISIBLE
        } else {
            ll_empty.visibility = View.GONE
        }
    }

    override fun onFail(msg: String) {
//        refresh_layout.finishRefresh()
        showToast(msg)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_all
    }

    override fun initView() {
        index = arguments?.getInt("index", 0)!!
        mPresenter.attachView(this)
//        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
//            override fun onLoadMore(refreshLayout: RefreshLayout) {
//                page++
//                getData()
//            }
//
//            override fun onRefresh(refreshLayout: RefreshLayout) {
//                page = 1
//                getData()
//            }
//        })

        recyclerView.layoutManager = object : LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        adapter = AllAdapter()
        adapter?.bindToRecyclerView(recyclerView)
        adapter!!.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener(){
            baseQuickAdapter: BaseQuickAdapter<Any, BaseViewHolder>, view: View, i: Int ->
            val intent= Intent(getActivity(), DetailActivity::class.java)
            startActivity(intent)
        })

    }
    fun getData() {
        mPresenter.getDataList(page)
    }

    override fun lazyLoad() {
        page = 1
        getData()
    }

    var page = 1
    var adapter: AllAdapter? = null
    val dataList = arrayListOf<ContentBean>()
    val mPresenter by lazy { AllPresenter(context!!) }
    var typeId = 0
    var index = 1

    companion object {
        fun getInstance(tab: Int): AllFragment {
            val fragment = AllFragment()
            val args = Bundle()
            args.putInt("tab", tab)
            fragment.arguments = args
            return fragment
        }
    }
}