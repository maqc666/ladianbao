package com.yifu.ladianbao.net

import android.app.ProgressDialog
import android.content.Context
import android.view.Window

import com.yifu.ladianbao.App
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseBean
import com.yifu.ladianbao.util.LoginUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import java.io.File


object Net {
    fun post(activity: Context, url: String, map: Map<String, Any>, netCallback: Callback) {
        var params = HttpParams()
        for ((k, v) in map) {
            if (v is File) {
                params.put(k, v)
            } else {
                params.put(k, v.toString())
            }
        }
        OkGo.post<BaseBean<Any>>(url).tag(activity).params(params).execute(object : OkGoCallback<BaseBean<Any>>() {
            override fun onSuccess(response: Response<BaseBean<Any>>) {
                super.onSuccess(response)
                netCallback.onSuccess(response?.body()?.data,response?.body()?.info)
                netCallback.onComplete()
            }

            override fun onStart(request: Request<BaseBean<Any>, out Request<*, *>>?) {
                super.onStart(request)
                netCallback.onStart()
            }

            override fun onFinish() {
                super.onFinish()
            }

            override fun onError(response: Response<BaseBean<Any>>) {
                super.onError(response)
                netCallback.onError(response.exception)
                if (response.exception is ApiException) {
                    when ((response.exception as ApiException).code) {
                        -1 -> {
                            LoginUtils.logout()
                            App.instance().reLogin()
                        }
                        else -> netCallback.onComplete()
                    }
                } else {
                    netCallback.onComplete()
                }
            }
        })
    }

    abstract class Callback(context: Context?, var isShow: Boolean = false, tag: String = context?.getString(R.string.connecting) ?: "") {
        constructor() : this(null, false, "")
        constructor(context: Context?, isShow: Boolean) : this(context, isShow, context?.getString(R.string.connecting) ?: "")

        lateinit var mDialog: ProgressDialog

        init {
            if (isShow) {
                mDialog = ProgressDialog(context)
                mDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                mDialog?.setCanceledOnTouchOutside(false)
                mDialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                mDialog?.setMessage(tag)
            }
        }

        open fun onStart() {
            try {
                if (isShow && !mDialog?.isShowing)
                    mDialog?.show()
            } catch(e: Exception) {
            }
        }

        open fun onComplete() {
            try {
                if (isShow && mDialog?.isShowing)
                    mDialog?.dismiss()
            } catch(e: Exception) {
            }
        }

        abstract fun onSuccess(t: Any?,info:String?)
        abstract fun onError(apiException: Throwable?)

    }
}