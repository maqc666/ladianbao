package com.yifu.ladianbao.ui.logout

import com.yifu.ladianbao.base.IBaseView
import com.yifu.ladianbao.base.IPresenter
import com.yifu.ladianbao.ui.login.UserBean

interface LogoutContract {

    interface View : IBaseView {
        fun onLogoutSuccess(bean: LogoutBean)
        fun onLogoutFail(msg: String)
    }

    interface Presenter: IPresenter<View>{
        fun Logout(app_token: String)
    }

}