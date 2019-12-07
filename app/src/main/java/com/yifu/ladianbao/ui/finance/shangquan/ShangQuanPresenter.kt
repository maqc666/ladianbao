package com.yifu.ladianbao.ui.finance.shangquan

import android.content.Context
import com.lzy.okgo.OkGo
import com.yifu.ladianbao.base.BaseKPresenter
import com.yifu.ladianbao.net.BaseResponse
import com.yifu.ladianbao.net.JsonCallback
import com.yifu.ladianbao.net.UrlUtils
import com.yifu.ladianbao.ui.finance.business.BusinessBean
import com.yifu.ladianbao.ui.finance.business.BusinessListBean
import okhttp3.Response

class ShangQuanPresenter (context: Context)  : BaseKPresenter<ShangQuanContract.View>(context), ShangQuanContract.Persenter {
    override fun getDataList(p: Int,token:String) {

        OkGo.get<BaseResponse<ShangQuanBean>>(UrlUtils.circleReward)
                .tag(mRootView)
                .headers("token", token)
                .execute(object : JsonCallback<BaseResponse<ShangQuanBean>>(mContext, true) {
                    override fun onSuccess(success: BaseResponse<ShangQuanBean>?) {
                        success?.data?.let { mRootView?.onDataSuccess(it) }
                    }

                    override fun onError(response: com.lzy.okgo.model.Response<BaseResponse<ShangQuanBean>>?) {
                        super.onError(response)
                        mRootView?.onDataFail(response?.exception?.message.toString())
                    }
                })
    }

    override fun getDetailList(p: Int,token:String) {

        OkGo.post<BaseResponse<BusinessListBean>>(UrlUtils.circleReward)
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