package com.yifu.ladianbao.ui.systemmanage.partner.parnerdetail.xiugai


import android.view.View
import android.widget.TextView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import kotlinx.android.synthetic.main.activity_modify.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import java.util.ArrayList

class ModifyActivity : BaseKActivity() {

    private var pvCustomOptions: OptionsPickerView<String>? = null
    private val cardItem = ArrayList<String>()

    override fun layoutId(): Int {
        return R.layout.activity_modify
    }

    override fun initView() {
        tv_title.text="信息修改"
        initCustomOptionPicker()
        getCardItem()
        image_back.setOnClickListener{
            finish()
        }
        ll_bank.setOnClickListener{
            pvCustomOptions!!.show()
        }

         }

    override fun initData() {

         }

    private fun initCustomOptionPicker() {
        var selectindex = 6
        if (tv_bank.text.isNotEmpty()) {
            for (i in 0 until cardItem.size) {
                if (cardItem[i] == tv_bank.text.toString()) {
                    selectindex = i
                    break
                }
            }
        }
        pvCustomOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            val tx = cardItem[options1]
            tv_bank!!.text = tx
//                mPresenter.onUserInfo("", "", "", "", "", "",
//                        "", "", "", tx, "", "")
        })
                .setLayoutRes(R.layout.pickerview_custom_option) { v ->
                    val tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
                    tvSubmit.setOnClickListener { v1 ->
                        pvCustomOptions!!.returnData()
                        pvCustomOptions!!.dismiss()
                    }
                    val tvConfirm = v.findViewById<View>(R.id.tv_confirm) as TextView
                    tvConfirm.setOnClickListener { v1 ->

                        pvCustomOptions!!.dismiss()
                    }
                }
                .isDialog(false)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setOutSideCancelable(false)
                .build<String>()

        pvCustomOptions!!.setPicker(cardItem)//添加数据
        pvCustomOptions!!.setSelectOptions(selectindex)
    }
    //身高数据
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


}
