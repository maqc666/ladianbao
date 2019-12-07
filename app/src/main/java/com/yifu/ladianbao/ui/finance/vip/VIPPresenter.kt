package com.yifu.ladianbao.ui.finance.vip

import android.content.Context
import com.lzy.okgo.OkGo
import com.yifu.ladianbao.base.BaseKPresenter
import com.yifu.ladianbao.net.BaseResponse
import com.yifu.ladianbao.net.JsonCallback
import com.yifu.ladianbao.net.UrlUtils
import com.yifu.ladianbao.ui.finance.business.BusinessListBean
import com.yifu.ladianbao.ui.finance.shangquan.ShangQuanBean

class VIPPresenter(context: Context)  : BaseKPresenter<VIPContract.View>(context), VIPContract.Persenter {
    override fun getDataList(p: Int,token:String) {

        OkGo.get<BaseResponse<VIPBean>>(UrlUtils.supremeReward)
                .tag(mRootView)
                .headers("token", token)
                .execute(object : JsonCallback<BaseResponse<VIPBean>>(mContext, true) {
                    override fun onSuccess(success: BaseResponse<VIPBean>?) {
                        success?.data?.let { mRootView?.onDataSuccess(it) }
                    }

                    override fun onError(response: com.lzy.okgo.model.Response<BaseResponse<VIPBean>>?) {
                        super.onError(response)
                        mRootView?.onDataFail(response?.exception?.message.toString())
                    }
                })
    }

    override fun getDetailList(p: Int,token:String) {

        OkGo.post<BaseResponse<BusinessListBean>>(UrlUtils.supremeReward)
                .tag(mRootView)
                .headers("token", token)
                .params("type",2)
                .params("page",p)
                .execute(object : JsonCallback<BaseResponse<BusinessListBean>>(mContext, true) {
                    override fun onSuccess(success: BaseResponse<BusinessListBean>?) {
                        success?.data?.let { mRootView?.onDetailSuccess(it) }
                    }

                    override fun onError(response: com.lzy.okgo.model.Response<BaseResponse<BusinessListBean>>?) {
                        super.onError(response)
                        mRootView?.onDataFail(response?.exception?.message.toString())
                    }
                })
    }
}