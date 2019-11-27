package com.yifu.ladianbao.ui.systemmanage.partner.createcharacter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import kotlinx.android.synthetic.main.activity_creater_char.*
import kotlinx.android.synthetic.main.activity_creater_char.tv_bank
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import java.util.ArrayList

class CreaterCharActivity : BaseKActivity() {

    private var pvBankOptions: OptionsPickerView<String>? = null
    private val cardItem = ArrayList<String>()
    private var pvAreaOptions: OptionsPickerView<String>? = null
    private val AreaItem = ArrayList<String>()


    override fun layoutId(): Int {
        return R.layout.activity_creater_char
    }

    override fun initView() {
        tv_title.text = "增加合伙人"
        image_back.setOnClickListener {
            finish()
        }

        ll_bank.setOnClickListener {
            pvBankOptions!!.show()
        }

        ll_area.setOnClickListener {
            pvAreaOptions!!.show()
        }




        initCustomOptionPicker()
        getCardItem()

        initAreaOptionPicker()
        getAreaItem()

    }

    override fun initData() {
    }
    //银行弹窗
    private fun initCustomOptionPicker() {
        var selectindex = 0
        if (tv_bank.text.isNotEmpty()) {
            for (i in 0 until cardItem.size) {
                if (cardItem[i] == tv_bank.text.toString()) {
                    selectindex = i
                    break
                }
            }
        }
        pvBankOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            val tx = cardItem[options1]
            tv_bank!!.text = tx
//                mPresenter.onUserInfo("", "", "", "", "", "",
//                        "", "", "", tx, "", "")
        })
                .setLayoutRes(R.layout.pickerview_custom_option) { v ->
                    val tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
                    tvSubmit.setOnClickListener { v1 ->
                        pvBankOptions!!.returnData()
                        pvBankOptions!!.dismiss()
                    }
                    val tvConfirm = v.findViewById<View>(R.id.tv_confirm) as TextView
                    tvConfirm.setOnClickListener { v1 ->

                        pvBankOptions!!.dismiss()
                    }
                }
                .isDialog(false)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setOutSideCancelable(false)
                .build<String>()

        pvBankOptions!!.setPicker(cardItem)//添加数据
        pvBankOptions!!.setSelectOptions(selectindex)
    }

    //银行数据
    private fun getCardItem() {
        cardItem.add("平安银行")
        cardItem.add("浙商银行")
        cardItem.add("中国银行")
        cardItem.add("中国建设银行")
        cardItem.add("上海浦东发展银行")
        cardItem.add("招商银行")
        cardItem.add("中国光大银行")
        cardItem.add("华夏银行")
        cardItem.add("兴业银行")
        cardItem.add("中国工商银行")
        cardItem.add("中国农业银行")
        cardItem.add("临商银行")
        cardItem.add("山东农信")
    }

    //地区弹窗
    private fun initAreaOptionPicker() {
        var selectindex = 0
        if (tv_area.text.isNotEmpty()) {
            for (i in 0 until AreaItem.size) {
                if (AreaItem[i] == tv_area.text.toString()) {
                    selectindex = i
                    break
                }
            }
        }
        pvAreaOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            val tx = AreaItem[options1]
            tv_area!!.text = tx
//                mPresenter.onUserInfo("", "", "", "", "", "",
//                        "", "", "", tx, "", "")
        })
                .setLayoutRes(R.layout.pickerview_custom_option) { v ->
                    val tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
                    tvSubmit.setOnClickListener { v1 ->
                        pvAreaOptions!!.returnData()
                        pvAreaOptions!!.dismiss()
                    }
                    val tvConfirm = v.findViewById<View>(R.id.tv_confirm) as TextView
                    tvConfirm.setOnClickListener { v1 ->

                        pvAreaOptions!!.dismiss()
                    }
                }
                .isDialog(false)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setOutSideCancelable(false)
                .build<String>()

        pvAreaOptions!!.setPicker(AreaItem)//添加数据
        pvAreaOptions!!.setSelectOptions(selectindex)
    }

    //地区数据
    private fun getAreaItem() {
        AreaItem.add("兰山区")
        AreaItem.add("罗庄区")
        AreaItem.add("河东区")
        AreaItem.add("沂南县")
        AreaItem.add("郯城县")
        AreaItem.add("沂水县")
        AreaItem.add("苍山县")
        AreaItem.add("费县")
        AreaItem.add("平邑县")
        AreaItem.add("蒙阴县")
        AreaItem.add("临沭县")
    }
}
