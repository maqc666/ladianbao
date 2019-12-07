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

    override fun getIncomeList(token: String,p: Int) {
        OkGo.post<BaseResponse<BusinessListBean>>(UrlUtils.myWallet)
                .tag(mRootView)
                .headers("token", token)
                .params("type",2)
                .params("page",p)
                .execute(object : JsonCallback<BaseResponse<BusinessListBean>>(mContext, true) {
                    override fun onSuccess(success: BaseResponse<BusinessListBean>?) {
                        success?.data?.let { mRootView?.onDataSuccess(it) }
                    }

                    override fun onError(response: Response<BaseResponse<BusinessListBean>>?) {
                        super.onError(response)
                        mRootView?.onDataFail(response?.exception?.message.toString())
                    }
                })
    }


    override fun getCashList(token: String,p: Int) {

        OkGo.post<BaseResponse<BusinessListBean>>(UrlUtils.myWallet)
                .tag(mRootView)
                .headers("token", token)
                .params("type",1)
                .params("page",p)
                .execute(object : JsonCallback<BaseResponse<BusinessListBean>>(mContext!!, true) {
                    override fun onSuccess(success: BaseResponse<BusinessListBean>?) {
                        success?.data?.let { mRootView?.onCashSuccess(it) }
                    }

                    override fun onError(response: Response<BaseResponse<BusinessListBean>>?) {
                        super.onError(response)
                        mRootView?.onDataFail(response?.exception?.message.toString())
                    }
                })
}}