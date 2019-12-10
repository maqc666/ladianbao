package com.yifu.ladianbao.ui.finance.business.businesslist

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseLazyFragment
import com.yifu.ladianbao.ui.finance.business.BusinessAdapter
import com.yifu.ladianbao.ui.finance.business.BusinessListBean
import com.yifu.ladianbao.util.LoginUtils
import kotlinx.android.synthetic.main.activity_business.*
import kotlinx.android.synthetic.main.fragment_business_list.*

class BusinesslistFragment : BaseLazyFragment<BusinessListContract.Present>(), BusinessListContract.View {
    override fun onDataFail(msg: String) {

    }
    fun getBusinessList(){
        mPresenter.getIncomeList(LoginUtils.getToken(),p,income)

    }
    override fun initAll() {

        income = arguments?.getInt("income", 1)!!
//        cash = arguments?.getInt("cash", 1)!!


           refresh_layout_list.setEnableRefresh(false)
        getBusinessList()
            refresh_layout_list.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                p++
                getBusinessList()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {


            }
        })



        adapter = BusinessAdapter()
       // adapter.cash = cash
//        adapter.setOnItemClickListener { adapter, view, position ->
//            if(data[position].status!=0){
//                startActivity<TraceInfoActivity>("orderid" to data[position].order_id.toString())
//            }
//        }
        rv_business_list.layoutManager = LinearLayoutManager(mContext)
        rv_business_list.adapter = adapter
    }

    override fun setListener() {
         }

    override fun processLogic() {
        }

    override fun loadData(boolean: Boolean) {
          }

    override fun getContext(): Context {
        return mContext
        }

    override fun onDataSuccess(bean: BusinessListBean) {
        if (bean.data.size<= 0) {
            rv_business_list.visibility=View.GONE
            ll_empty_list.visibility = View.VISIBLE
        } else {
            ll_empty_list.visibility = View.GONE
        }
        if (p==1) {
            dataList.clear()
            refresh_layout_list.finishRefresh()
        }
        if (bean.data.size < 10) {
            refresh_layout_list.finishLoadMoreWithNoMoreData()
        }
        dataList.addAll(bean.data)
        refresh_layout_list.finishLoadMore()
        adapter?.setNewData(dataList)

         }

    override fun onEmpty() {
        }

    override fun onError() {
        }

    companion object {
        fun getInstance(income: Int): BusinesslistFragment {
            val fragment = BusinesslistFragment()
            val bundle = Bundle()
            bundle.putInt("income", income)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var p = 1
    private var dataList = arrayListOf<BusinessListBean.DataBean>()
    lateinit var adapter: BusinessAdapter

    //2收入 1提现
    var income = 1

    override val mPresenter: BusinessListContract.Present
        get() = BusinessListPresenter().also { it.attachView(this) }
    override val layoutRes: Int
        get() = R.layout.fragment_business_list
}