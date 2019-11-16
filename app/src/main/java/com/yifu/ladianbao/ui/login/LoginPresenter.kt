package com.yifu.ladianbao.ui.login

import com.yifu.ladianbao.net.BaseResponse
import com.yifu.ladianbao.net.JsonCallback
import com.yifu.ladianbao.net.UrlUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response

class LoginPresenter :  LoginContract.Presenter(){
    override fun login(mobile: String, password: String,type:String) {
        OkGo.post<BaseResponse<UserBean>>(UrlUtils.login)
                .tag(mView)
                .params("mobile", mobile)
                .params("password", password)
                .params("type",type)
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

}