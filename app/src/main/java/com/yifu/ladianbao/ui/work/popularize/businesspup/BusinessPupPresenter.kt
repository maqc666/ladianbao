package com.yifu.ladianbao.ui.work.popularize.businesspup

import android.content.Context
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.yifu.ladianbao.base.BaseKPresenter
import com.yifu.ladianbao.net.BaseResponse
import com.yifu.ladianbao.net.JsonCallback
import com.yifu.ladianbao.net.UrlUtils

class BusinessPupPresenter(context: Context)  : BaseKPresenter<BusinessPupContract.View>(context), BusinessPupContract.Persenter {
    override fun getDataList(token: String) {
        OkGo.post<BaseResponse<CircleBean>>(UrlUtils.circleExtension)
                .tag(mRootView)
                .headers("token", token)
                .execute(object : JsonCallback<BaseResponse<CircleBean>>(mContext!!, true) {
                    override fun onSuccess(success: BaseResponse<CircleBean>?) {
                        success?.data?.let { mRootView?.onDataSuccess(it) }
                    }

                    override fun onError(response: Response<BaseResponse<CircleBean>>?) {
                        super.onError(response)
                        mRootView?.onDataFail(response?.exception?.message.toString())
                    }
                })

    }
}