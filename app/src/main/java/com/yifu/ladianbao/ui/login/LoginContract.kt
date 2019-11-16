package com.yifu.ladianbao.ui.login

import com.yifu.ladianbao.base.BasePresent
import com.yifu.ladianbao.base.BaseView

class LoginContract {

    interface View : BaseView {
        fun onLoginSuccess(bean: UserBean)
        fun onLoginFail(msg: String)
    }

    abstract class Presenter : BasePresent<View>() {
        abstract fun login(username: String, password: String,type: String)
    }
}