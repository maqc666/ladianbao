package com.yifu.ladianbao.ui.post_sale.caigou.pay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class PayActivity : BaseKActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_pay
    }

    override fun initView() {
        tv_title.text="订单支付"
        image_back.setOnClickListener { finish() }

         }

    override fun initData() {
         }


}
