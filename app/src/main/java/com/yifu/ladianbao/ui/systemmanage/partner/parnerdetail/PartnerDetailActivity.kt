package com.yifu.ladianbao.ui.systemmanage.partner.parnerdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.systemmanage.partner.parnerdetail.xiugai.ModifyActivity
import kotlinx.android.synthetic.main.activity_parner_detail.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import org.jetbrains.anko.startActivity

class PartnerDetailActivity : BaseKActivity() {

    private var pvHuaboOptions: OptionsPickerView<String>? = null
    override fun layoutId(): Int {
        return R.layout.activity_parner_detail
    }
    override fun initView() {

        tv_title.text="合伙人详情"
        image_back.setOnClickListener{
            finish()
        }
        tv_huabo.setOnClickListener{
            pvHuaboOptions!!.show()

        }
        tv_xiugai.setOnClickListener{
            this.startActivity<ModifyActivity>()
        }

        initHuaboOptionPicker()
         }

    override fun initData() {
          }

    private fun initHuaboOptionPicker() {

        pvHuaboOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
//            val tx = cardItem[options1]
//            tv_bank!!.text = tx
//                mPresenter.onUserInfo("", "", "", "", "", "",
//                        "", "", "", tx, "", "")
        })
                .setLayoutRes(R.layout.pickerview_huabo_option) { v ->
                    val tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
                    tvSubmit.setOnClickListener { v1 ->
                        pvHuaboOptions!!.returnData()
                        pvHuaboOptions!!.dismiss()
                    }
                    val tvConfirm = v.findViewById<View>(R.id.tv_confirm) as TextView
                    tvConfirm.setOnClickListener { v1 ->

                        pvHuaboOptions!!.dismiss()
                    }
                }
                .isDialog(true)
                .setOutSideCancelable(true)
                .build<String>()

//        pvCustomOptions!!.setPicker(cardItem)//添加数据
//        pvCustomOptions!!.setSelectOptions(selectindex)
    }
}
