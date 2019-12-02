package com.yifu.ladianbao.ui.logout

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseActivity
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.login.LoginActivity
import com.yifu.ladianbao.ui.login.UserBean
import com.yifu.ladianbao.util.LoginUtils
import kotlinx.android.synthetic.main.activity_logout.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import org.jetbrains.anko.startActivity

class LogoutActivity :  BaseKActivity(), LogoutContract.View {
    override fun onLogoutSuccess(bean: LogoutBean) {
        LoginUtils.logout()
        this.startActivity<LoginActivity>()
        finish()
    }

    override fun onLogoutFail(msg: String) {
        showToast(msg)
         }

    override fun onFail(msg: String) {
        }

    override fun layoutId(): Int {
        return R.layout.activity_logout
    }

    override fun initView() {
        tv_title.text="设置"
        image_back.setOnClickListener{
            finish()
        }
        tv_logout.setOnClickListener {
            LoginUtils.logout()
            this.startActivity<LoginActivity>()
            finish()
            //mPresenter.Logout(LoginUtils.getToken())
        } }

    override fun initData() {

         }

    val mPresenter by lazy { LogoutPresenter(mContext) }



}
