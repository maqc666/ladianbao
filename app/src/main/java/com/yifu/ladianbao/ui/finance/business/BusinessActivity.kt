package com.yifu.ladianbao.ui.finance.business


import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager


import android.view.View

import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseFragmentAdapter
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.finance.business.businesslist.BusinesslistFragment
import com.yifu.ladianbao.util.LoginUtils
import kotlinx.android.synthetic.main.activity_business2.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import org.jetbrains.anko.textColor

class BusinessActivity : BaseKActivity(),BusinessContract.View {

    override fun onSuccess(bean: BusinessBean) {
        tv_money.text=bean.money
        tv_commission.text=bean.commission
        tv_yesterday.text=bean.commission_yesterday
    }

    override fun onDataFail(msg: String) {
        showToast(msg)
    }



    override fun onFail(msg: String) {
        showToast(msg)  }

    override fun layoutId(): Int {
        return R.layout.activity_business2
    }


    override fun initView() {
        initState()

        tv_title.text = "交易分润"
        image_back.setOnClickListener {
            finish()
        }
        ll_title.visibility= View.VISIBLE

//        refresh_layout.set






        mPresenter.attachView(this)
//        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
//            override fun onLoadMore(refreshLayout: RefreshLayout) {
//                page++
//                initData()
//            }
//
//            override fun onRefresh(refreshLayout: RefreshLayout) {
//                page = 1
//                initData()
//            }
//        })

//




    }

    private fun initState(){
        setItemPage(0)
        tv_income.setOnClickListener {
            viewPager.setCurrentItem(0, true)
            setItemPage(0)
        }
        tv_cash.setOnClickListener {
            viewPager.setCurrentItem(1, true)
            setItemPage(1)
        }
        fragments.add(BusinesslistFragment.getInstance(2))
        fragments.add(BusinesslistFragment.getInstance(1))

        adapter = BaseFragmentAdapter(supportFragmentManager, fragments)

        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setItemPage(position)
            }
        })

    }

    private fun setItemPage(position: Int) {
        when (position) {
            0 -> {
                tv_cash.textColor = ContextCompat.getColor(mContext, R.color.black)
                tv_income.textColor = ContextCompat.getColor(mContext, R.color.color_main)
            }
            1 -> {
                tv_cash.textColor = ContextCompat.getColor(mContext, R.color.color_main)
                tv_income.textColor = ContextCompat.getColor(mContext, R.color.black)
            }
        }
    }
    override fun initData() {
        mPresenter.getDataList(LoginUtils.getToken())

         }
    var page = 1
    private val fragments = arrayListOf<Fragment>()
    private lateinit var adapter: BaseFragmentAdapter
    val dataList = arrayListOf<BusinessListBean.DataBean>()
    val mPresenter by lazy { BusinessPresenter(this) }
    var typeId = 0
    var index = 1

}
