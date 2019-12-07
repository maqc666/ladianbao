package com.yifu.ladianbao.net

import android.content.Context
import android.view.View
import android.widget.ProgressBar
/*import cn.jpush.android.api.JPushInterface*/
import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.yifu.ladianbao.App
import com.yifu.ladianbao.util.Convert
import com.yifu.ladianbao.util.LoginUtils
import com.yifu.ladianbao.util.utilcode.ToastUtils
/*import com.lnkj.inyouquan.utils.Convert
import com.lnkj.inyouquan.MyApplication
import com.lnkj.inyouquan.utils.LoginUtils
import com.lnkj.inyouquan.utils.utilcode.ToastUtils*/
import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.exception.HttpException
import com.lzy.okgo.exception.StorageException
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import org.json.JSONException
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException


abstract class JsonCallback<T>(context: Context, canShow: Boolean = false, message: String = "加载中...") : AbsCallback<T>() {

    private val context: Context = context
    private val progressBar: ProgressBar = ProgressBar(context)
    private val canShow: Boolean = canShow

    init {
        if (progressBar.isShown && canShow) {
            progressBar.visibility = View.GONE
        }
    }

    override fun onStart(request: Request<T, out Request<Any, Request<*, *>>>?) {
        super.onStart(request)
        try {
            if (!progressBar.isShown && canShow) {
                progressBar.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onSuccess(response: Response<T>?) {
        try {
            if (progressBar.isShown && canShow) {
                progressBar.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        onSuccess(response?.body())
    }

    abstract fun onSuccess(success: T?)

    override fun onError(response: Response<T>?) {
        super.onError(response)
        try {
            if (progressBar.isShown && canShow) {
                progressBar.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val exception = response?.exception
        exception?.printStackTrace()
        when (exception) {
            is UnknownHostException -> ToastUtils.showShort("网络连接失败,请连接网络")
            is ConnectException -> ToastUtils.showShort("网络连接失败,请连接网络")
            is SocketException -> ToastUtils.showShort("网络请求超时")
            is HttpException -> ToastUtils.showShort("服务器异常")
            is StorageException -> ToastUtils.showShort("sd卡不存在或者没有权限")
            is JsonParseException -> ToastUtils.showShort("数据异常")
            is JSONException -> ToastUtils.showShort("数据异常")
            is IllegalStateException -> ToastUtils.showShort(exception.localizedMessage)
            else -> ToastUtils.showShort("未知异常")
        }
    }

    override fun convertResponse(response: okhttp3.Response?): T {
        val genType = javaClass.genericSuperclass
        val params = (genType as ParameterizedType).actualTypeArguments
        val type = params[0] as? ParameterizedType ?: throw IllegalStateException("没有填写泛型参数")
        val rawType = type.rawType
        val typeArgument = type.actualTypeArguments[0]
        val body = response?.body()
        response?.close()
        if (body == null) throw IllegalStateException("响应失败")
        val mResponse = body.string()
//        LogUtils.v(mResponse)
        val gson = Gson()
        if (rawType !== BaseResponse::class.java) {
            return gson.fromJson(mResponse, type)
        } else {
            if (typeArgument === Void::class.java) {
                try {
                    val simpleResponse = Convert.fromJson(mResponse, SimpleResponse::class.java)
                    val code = JSON.parseObject(mResponse).getInteger("status")
                    return if (code == 1) {
                        simpleResponse.toBaseResponse() as T
                    } else {
                        throw IllegalStateException(simpleResponse.info)
                    }
                } catch (e: Exception) {
                    throw IllegalStateException(e.message)
                }

            } else {
                val code = JSON.parseObject(mResponse).getInteger("code")
                val data = JSON.parseObject(mResponse).getString("data")
                val msg = JSON.parseObject(mResponse).getString("msg")
                when {
//                    code == 1 -> return if (result == "[]" || result == "{}" || TextUtils.isEmpty(result)){
//                        val simpleResponse = Convert.fromJson(mResponse, SimpleResponse::class.java)
//                        simpleResponse.toBaseResponse() as T
//                    } else {
//                        gson.fromJson(mResponse, type) as T
//                    }
                    code == 1 -> return gson.fromJson(mResponse, type) as T
                    code == 401 -> {
                        LoginUtils.logout()
                        App.instance().reLogin()
                        /*  if (!JPushInterface.isPushStopped(context)) { // 检查 Push Service 是否已经被停止
                              JPushInterface.stopPush(context) // 极光推送服务会被停止掉
                          }*/
//                        context.startActivity<LoginActivity>()
                        throw IllegalStateException("未登录或登录已过期,请重新登录")
                    }
//                    code == -1 -> throw IllegalStateException(JSON.parseObject(mResponse).getString("info"))
                    else -> throw IllegalStateException(JSON.parseObject(mResponse).getString("msg"))
                }
            }
        }
    }


}