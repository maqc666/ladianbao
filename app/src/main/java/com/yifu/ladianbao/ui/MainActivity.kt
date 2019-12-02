package com.yifu.ladianbao.ui



import android.content.Intent
import android.view.KeyEvent

import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.finance.business.BusinessActivity
import com.yifu.ladianbao.ui.finance.shangquan.ShangQuanActivity
import com.yifu.ladianbao.ui.finance.vip.VIPActivity
import com.yifu.ladianbao.ui.logout.LogoutActivity
import com.yifu.ladianbao.ui.post_sale.new_policy.PolicyActivity
import com.yifu.ladianbao.ui.post_sale.setting.SettingActivity
import com.yifu.ladianbao.ui.systemmanage.order.OrderActivity
import com.yifu.ladianbao.ui.systemmanage.partner.PartnerActivity
import com.yifu.ladianbao.ui.systemmanage.syslist.SysListActivity
import com.yifu.ladianbao.ui.work.popularize.PopularActivity

import com.yifu.ladianbao.util.utilcode.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_titlebar.*
import org.jetbrains.anko.startActivity


class MainActivity : BaseKActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initTab()
        tv_title.text="个人中心"

        setting.setOnClickListener{
            this.startActivity<LogoutActivity>()
        }

        ll_shop_list.setOnClickListener {
            val intent = Intent (this,SysListActivity::class.java)
            startActivity(intent)
        }
        ll_partner.setOnClickListener {

            val intent=Intent(this,PartnerActivity::class.java)
            startActivity(intent)
        }
        ll_order.setOnClickListener {
            this.startActivity<OrderActivity>()
        }
        ll_business.setOnClickListener {
            this.startActivity<BusinessActivity>()
        }
        ll_shangquan.setOnClickListener {
            this.startActivity<ShangQuanActivity>()
        }
        ll_vip.setOnClickListener {
            this.startActivity<VIPActivity>()
        }
        ll_setting.setOnClickListener {
            this.startActivity<SettingActivity>()
        }
        ll_pop.setOnClickListener {
            this.startActivity<PopularActivity>()
        }
        ll_policy.setOnClickListener {
            this.startActivity<PolicyActivity>()
        }
        }

    override fun initData() {
        }


    private fun initTab() {

    }
    /**
     * 底部按钮点击事件
     */
    fun setFragmentShow(index: Int) {

    }
    /**
     * 后退
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtils.showShort("再按一次退出程序")
                exitTime = System.currentTimeMillis()
            } else {
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    private var exitTime: Long = 0


}
