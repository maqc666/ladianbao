package com.yifu.ladianbao.ui.systemmanage.syslist.all.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class DetailActivity : BaseKActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_detail
    }

    override fun initView() {
        tv_title.text="系统详情"
        image_back.setOnClickListener{
            finish()
        }
        }

    override fun initData() {
        }


}
