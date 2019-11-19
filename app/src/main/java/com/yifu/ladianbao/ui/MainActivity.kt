package com.yifu.ladianbao.ui



import android.view.KeyEvent

import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity

import com.yifu.ladianbao.util.utilcode.ToastUtils
import kotlinx.android.synthetic.main.layout_titlebar.*


class MainActivity : BaseKActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initTab()
        tv_title.text="个人中心"



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
