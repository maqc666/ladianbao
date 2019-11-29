package com.yifu.ladianbao.ui.logout

import android.content.Context
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.yifu.ladianbao.base.BaseKPresenter
import com.yifu.ladianbao.net.BaseResponse
import com.yifu.ladianbao.net.JsonCallback
import com.yifu.ladianbao.net.UrlUtils
import com.yifu.ladianbao.ui.login.LoginContract
import com.yifu.ladianbao.ui.login.UserBean
import com.yifu.ladianbao.util.LoginUtils

class LogoutPresenter (context: Context)  : BaseKPresenter<LogoutContract.View>(context), LogoutContract.Presenter {


    override fun Logout(app_token: String) {
        OkGo.post<BaseResponse<LogoutBean>>(UrlUtils.logout)
                .tag(mRootView)
                .params("app_token",app_token)
                .execute(object : JsonCallback<BaseResponse<LogoutBean>>(mContext!!, true) {
                    override fun onSuccess(success: BaseResponse<LogoutBean>?) {
                        success?.data?.let { mRootView?.onLogoutSuccess(it) }
                    }

                    override fun onError(response: Response<BaseResponse<LogoutBean>>?) {
                        super.onError(response)
                        mRootView?.onLogoutFail(response?.exception?.message.toString())
                    }
                })
    }

}