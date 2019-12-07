package com.yifu.ladianbao.ui.systemmanage.partner.huaborecord

import android.support.v7.widget.LinearLayoutManager
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import kotlinx.android.synthetic.main.activity_huabo_record.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class HuaboRecordActivity : BaseKActivity(),HuaborecordContract.View {
    override fun onDataSuccess(list: List<HuaboBean>) {
        dataList.addAll(list)
        adapter?.setNewData(dataList)
    }

    override fun onFail(msg: String) {
        showToast(msg)
        }

    override fun layoutId(): Int {
        return R.layout.activity_huabo_record
    }

    override fun initView() {
        tv_title.text="划拨记录"
        image_back.setOnClickListener{
            finish()
        }
        mPresenter.attachView(this)
        rv_huabo.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        adapter = HuaboAdapter()
        adapter?.bindToRecyclerView(rv_huabo)


    }

    override fun initData() {
        mPresenter.getDataList(page)
        }

    var page = 1
    var adapter: HuaboAdapter? = null
    val dataList = arrayListOf<HuaboBean>()
    val mPresenter by lazy { HuaboPresenter(this) }
    var typeId = 0
    var index = 1
}
