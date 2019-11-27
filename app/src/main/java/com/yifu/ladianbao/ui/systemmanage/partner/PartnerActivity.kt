package com.yifu.ladianbao.ui.systemmanage.partner

import android.support.v7.widget.LinearLayoutManager
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.systemmanage.partner.createcharacter.CreaterCharActivity
import com.yifu.ladianbao.ui.systemmanage.partner.huaborecord.HuaboRecordActivity
import com.yifu.ladianbao.ui.systemmanage.partner.parnerdetail.PartnerDetailActivity
import kotlinx.android.synthetic.main.activity_partner.*
import kotlinx.android.synthetic.main.item_partner_content.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import org.jetbrains.anko.startActivity
import kotlin.jvm.java as java1

class PartnerActivity : BaseKActivity(),PartnerContract.View {
    override fun onDataSuccess(list: List<PartnerBean>) {
        dataList.addAll(list)
        adapter?.setNewData(dataList)
    }

    override fun onFail(msg: String) {
        showToast(msg)
          }

    override fun layoutId(): Int {
        return R.layout.activity_partner
    }

    override fun initView() {
        tv_title.text="合伙人业务管理"
        image_back.setOnClickListener{
            finish()
        }

        mPresenter.attachView(this)
        rv_partner.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        adapter = ContentAdapter()
        adapter?.bindToRecyclerView(rv_partner)

        adapter!!.setOnItemClickListener{
            adapter,view,position->
            this.startActivity<PartnerDetailActivity>()

        }

        tv_create_char.setOnClickListener {
            this.startActivity<CreaterCharActivity>()
        }

        tv_huabo_record.setOnClickListener {
            this.startActivity<HuaboRecordActivity>()
        }


//       -+
         }

    override fun initData() {
        mPresenter.getDataList(page)
         }

    var page = 1
    var adapter: ContentAdapter? = null
    val dataList = arrayListOf<PartnerBean>()
    val mPresenter by lazy { PartnerPresenter(this) }
    var typeId = 0
    var index = 1
}
