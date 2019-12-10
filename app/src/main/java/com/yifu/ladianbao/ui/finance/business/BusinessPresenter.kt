package com.yifu.ladianbao.ui.finance.business

import android.content.Context
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.yifu.ladianbao.base.BaseKPresenter
import com.yifu.ladianbao.net.BaseResponse
import com.yifu.ladianbao.net.JsonCallback
import com.yifu.ladianbao.net.UrlUtils

class BusinessPresenter (context: Context)  : BaseKPresenter<BusinessContract.View>(context), BusinessContract.Persenter {
    override fun getDataList(token: String) {
        OkGo.get<BaseResponse<BusinessBean>>(UrlUtils.myWallet)
                .tag(mRootView)
                .headers("token", token)
                .execute(object : JsonCallback<BaseResponse<BusinessBean>>(mContext, true) {
                    override fun onSuccess(success: BaseResponse<BusinessBean>?) {
                        success?.data?.let { mRootView?.onSuccess(it) }
                    }

                    override fun onError(response: Response<BaseResponse<BusinessBean>>?) {
                        super.onError(response)
                        mRootView?.onDataFail(response?.exception?.message.toString())
                    }
                })
    }


    }


