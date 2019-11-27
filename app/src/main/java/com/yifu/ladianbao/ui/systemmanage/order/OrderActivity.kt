package com.yifu.ladianbao.ui.systemmanage.order

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.systemmanage.order.sysorder.SysOrderActivty
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import org.jetbrains.anko.startActivity

class OrderActivity : BaseKActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_order
    }

    override fun initView() {
        tv_title.text="在线订购"
        image_back.setOnClickListener{
            finish()
        }

        tv_order.setOnClickListener {
          this.startActivity<SysOrderActivty>()
        }
         }

    override fun initData() {

         }


}
