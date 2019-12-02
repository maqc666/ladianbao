package com.yifu.ladianbao.ui.login

import com.yifu.ladianbao.net.BaseResponse
import com.yifu.ladianbao.net.JsonCallback
import com.yifu.ladianbao.net.UrlUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.yifu.ladianbao.util.LoginUtils

class LoginPresenter :  LoginContract.Presenter(){
    override fun login(username: String, password: String) {
        OkGo.post<BaseResponse<UserBean>>(UrlUtils.login)
                .tag(mView)
                .params("username", username)
                .params("password", password)
                .execute(object : JsonCallback<BaseResponse<UserBean>>(mContext!!, true) {
                    override fun onSuccess(success: BaseResponse<UserBean>?) {
                        success?.data?.let { mView?.onLoginSuccess(it) }
                    }

                    override fun onError(response: Response<BaseResponse<UserBean>>?) {
                        super.onError(response)
                        mView?.onLoginFail(response?.exception?.message.toString())
                    }
                })
    }

    override fun index( token: String,username: String, password: String) {
        OkGo.post<BaseResponse<UserBean>>(UrlUtils.login)
                .headers("token",token)
                .tag(mView)
                .params("username", username)
                .params("password", password)
                .execute(object : JsonCallback<BaseResponse<UserBean>>(mContext!!, true) {
                    override fun onSuccess(success: BaseResponse<UserBean>?) {
                        success?.data?.let { mView?.onIndexSuccess(it) }
                    }

                    override fun onError(response: Response<BaseResponse<UserBean>>?) {
                        super.onError(response)
                        mView?.onIndexFail(response?.exception?.message.toString())
                    }
                })
    }


}