package com.yifu.ladianbao.ui.work.popularize.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseFragment
import com.yifu.ladianbao.base.BaseKFragment
import com.yifu.ladianbao.base.BaseLazyFragment
import com.yifu.ladianbao.ui.systemmanage.syslist.all.AllPresenter
import kotlinx.android.synthetic.main.fragment_popular_all.*


class AllFragment: BaseKFragment(),AllContract.View {
    override fun onFail(msg: String) {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_popular_all

    }

    override fun initView() {
        mPresenter.attachView(this)
        mNowTab = arguments!!.getInt("tab")
        when (mNowTab) {
            0 -> {
                status = "0"
            }
            1 -> {
                status = "1"
            }
            2 -> {
                status = "2"
            }
        }

        adapter = AllItemAdapter()
        adapter.status = status
        adapter.setOnItemClickListener { adapter, view, position ->
            //var recommendBean = adapter.data[position] as CouponItemBean
//            var intent = Intent()
//            intent.setClass(mContext, CouponDetailActivity().javaClass)
//            startActivity(intent)
        }
//        adapter.setOnItemChildClickListener { adapter, view, position ->
//
//            when (view.id) {
//                R.id.ll_submit -> {
//
//                    when (status) {
//                        "0" -> {
//                            data[position].id?.let { mPresenter.setStatus(it, "1") }
//                        }
//                        "1" -> {
//                            data[position].id?.let { mPresenter.setStatus(it, "0") }
//                        }
//                        "2" -> {
//                            data[position].id?.let { mPresenter.del(it) }
//                        }
//                    }
//
//                }
//
//            }
//
//
//        }

        rv_popular.layoutManager = LinearLayoutManager(activity)
        rv_popular.adapter = adapter
        rv_popular.clearFocus()
        rv_popular.isFocusable = false
//        ll_add.setOnClickListener {
////            var activity = activity as CouponActivity
////            activity.showSignDialog()
//        }

    }

    override fun lazyLoad() {
        p = 1
        mPresenter.AllList(status,p)

          }
    var status = "0"



    override fun onAlllistSuccess(alist: List<AllItemBean>) {
        if (p == 1) {
            data.clear()
        }
        data.addAll(alist)
        adapter?.setNewData(data)
        }

    private var mNowTab: Int = 0
    private var p = 1
    private var data = arrayListOf<AllItemBean>()
    lateinit var adapter: AllItemAdapter
    val mPresenter by lazy { AllPersenter(context!!) }

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