package com.yifu.ladianbao.ui.logout

import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.login.LoginActivity
import com.yifu.ladianbao.util.LoginUtils
import kotlinx.android.synthetic.main.activity_logout.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import org.jetbrains.anko.startActivity
import android.content.Intent
import com.alibaba.fastjson.serializer.AwtCodec.support
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService







class LogoutActivity :  BaseKActivity(), LogoutContract.View {
    override fun onFail(msg: String) {

    }


    override fun onLogoutSuccess(msg: String) {
         showToast(msg)
    }

    override fun onLogoutFail(msg: String) {
        showToast(msg)

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
            mPresenter.Logout(LoginUtils.getToken())
            LoginUtils.logout()
            var intent = Intent()
            intent.setClass(this,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)


        } }

    override fun initData() {

         }

    val mPresenter by lazy { LogoutPresenter(mContext) }



}
