package com.yifu.ladianbao.ui.post_sale.new_policy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class PolicyActivity : BaseKActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_policy
    }

    override fun initView() {
        tv_title.text="最新政策"
        image_back.setOnClickListener{
            finish()
        }
         }

    override fun initData() {
        }


}
