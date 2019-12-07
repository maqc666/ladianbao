package com.yifu.ladianbao.ui.login

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.gyf.immersionbar.ktx.immersionBar
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseActivity
import com.yifu.ladianbao.ui.MainActivity
import com.yifu.ladianbao.util.LoginUtils
//import com.yifu.lakebao.ui.mine.changepwd.ChangePwdActivity
//import com.yifu.lakebao.ui.shopenter.shoplogin.ShopLoginActivity
import com.yifu.ladianbao.util.utilcode.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.reflect.Method

class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {
    override fun onIndexSuccess(bean: UserBean) {

        LoginUtils.saveToken(bean.token!!)
        LoginUtils.saveUserInfo(bean)
        val intent = Intent(mContext, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onIndexFail(msg: String) {
        showToast(msg)
        }

    override fun getContext(): Context {
        return mContext
    }

    override fun onEmpty() {
    }

    override fun onError() {

    }

    override val layoutRes: Int
        get() = R.layout.activity_login

    override fun initLogic() {
        immersionBar {
            //只适合纯色状态栏
            fitsSystemWindows(true)
            statusBarColor(R.color.color_first)
            statusBarDarkFont(false, 0.2f)
            keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
            keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
        }
    }

    override fun setListener() {
       show_hide_password.setOnClickListener {
           if (pwdIsShow) {
               //如果选中，显示密码
               pwdIsShow = false
               et_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
               show_hide_password.setBackgroundResource(R.mipmap.show)
           } else {
               //否则隐藏密码
               pwdIsShow = true
               et_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
               show_hide_password.setBackgroundResource(R.mipmap.unshow2)
           }
       }

        tv_login.setOnClickListener {



            if (et_mobile.text.toString().isEmpty()) {
                ToastUtils.showShort(et_mobile.hint)
                return@setOnClickListener
            }
            if (et_pwd.text.toString().isEmpty()) {
                ToastUtils.showShort(et_pwd.hint)
                return@setOnClickListener
            }

            mPresenter.login(et_mobile.text.toString(), et_pwd.text.toString())
        }
    }

    override fun processLogic() {
        if (LoginUtils.isLogin()) {
            val intent = Intent(mContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onLoginSuccess(bean: UserBean) {

       onIndexSuccess(bean)
    }

    override fun onLoginFail(msg: String) {
      showToast(msg)
    }


    override val mPresenter: LoginContract.Presenter
        get() = LoginPresenter().also { it.attachView(this) }

    var pwdIsShow = false

    fun disableShowInput(et: EditText) {
        val cls = EditText::class.java
        val method: Method
        try {
            method = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
            method.isAccessible = true
            method.invoke(et, false)
        } catch (e: Exception) {//TODO: handle exception
        }
    }


}
