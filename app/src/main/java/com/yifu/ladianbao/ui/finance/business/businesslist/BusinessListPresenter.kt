package com.yifu.ladianbao.ui.finance.business.businesslist

import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.yifu.ladianbao.net.BaseResponse
import com.yifu.ladianbao.net.JsonCallback
import com.yifu.ladianbao.net.UrlUtils
import com.yifu.ladianbao.ui.finance.business.BusinessListBean

class BusinessListPresenter : BusinessListContract.Present(){
    override fun getDataList(token: String) {

    }

    override fun getIncomeList(token: String,p: Int,income: Int) {
        OkGo.post<BaseResponse<BusinessListBean>>(UrlUtils.myWallet)
                .tag(mView)
                .headers("token", token)
                .params("type",income)
                .params("page",p)
                .execute(object : JsonCallback<BaseResponse<BusinessListBean>>(mContext!!, true) {
                    override fun onSuccess(success: BaseResponse<BusinessListBean>?) {
                        success?.data?.let { mView?.onDataSuccess(it) }
                    }

                    override fun onError(response: Response<BaseResponse<BusinessListBean>>?) {
                        super.onError(response)
                        mView?.onDataFail(response?.exception?.message.toString())
                    }
                })
    }


//    override fun getCashList(token: String,p: Int) {
//
//        OkGo.post<BaseResponse<BusinessListBean>>(UrlUtils.myWallet)
//                .tag(mView)
//                .headers("token", token)
//                .params("type",1)
//                .params("page",p)
//                .execute(object : JsonCallback<BaseResponse<BusinessListBean>>(mContext!!, true) {
//                    override fun onSuccess(success: BaseResponse<BusinessListBean>?) {
//                        success?.data?.let { mView?.onCashSuccess(it) }
//                    }
//
//                    override fun onError(response: Response<BaseResponse<BusinessListBean>>?) {
//                        super.onError(response)
//                        mView?.onDataFail(response?.exception?.message.toString())
//                    }
//                })
//    }}
}