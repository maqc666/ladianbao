package com.yifu.ladianbao.ui.finance.business

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class BusinessActivity : BaseKActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_business
    }

    override fun initView() {
        tv_title.text="交易分润"
        image_back.setOnClickListener{
            finish()
        }
          }

    override fun initData() {
         }


}
