package com.yifu.ladianbao.net

import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseBean
import com.yifu.ladianbao.util.utilcode.ToastUtils

import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.exception.HttpException
import com.lzy.okgo.exception.StorageException
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import org.json.JSONException
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException



abstract class OkGoCallback<T> : AbsCallback<T>() {

    override fun onStart(request: Request<T, out Request<*, *>>?) {
        super.onStart(request)
    }

    override fun onFinish() {
        super.onFinish()
    }

    override fun onError(response: Response<T>) {
        super.onError(response)
        val e = response.exception
        e?.printStackTrace()
        when {
            e is ApiException -> {
                ToastUtils.showShort(e.message)
            }
            e is HttpException -> {
                ToastUtils.showShort(R.string.exception_http)
            }
            e is JsonParseException || e is JSONException || e is ParseException -> {
                ToastUtils.showShort(R.string.exception_parse)
            }
            e is SocketTimeoutException -> {
                ToastUtils.showShort(R.string.exception_socket_time_out)
            }
            e is UnknownHostException || e is ConnectException -> {
                ToastUtils.showShort(R.string.exception_unkownhost)
            }
            e is StorageException -> {
                ToastUtils.showShort(R.string.exception_storage)
            }
            else -> {
                ToastUtils.showShort(e.message)
            }
        }
    }

    override fun onSuccess(response: Response<T>) {}

    @Throws(Throwable::class)
    override fun convertResponse(response: okhttp3.Response): T? {
        val genType = javaClass.genericSuperclass
        val params = (genType as ParameterizedType).actualTypeArguments
        val type = params[0] as? ParameterizedType ?: throw IllegalStateException("没有填写泛型参数")
        val rawType = type.rawType
        val body = response.body()
        response.close()
        if (body == null) return null
        val mResponse = body.string()
        val gson = Gson()
        if (rawType !== BaseBean::class.java) {
            val data = gson.fromJson<T>(mResponse, type)
            return data
        } else {
            val code = JSON.parseObject(mResponse).getInteger("status")
            val info = JSON.parseObject(mResponse).getString("info")
            if (code == 1) {
                val lazyResponse = gson.fromJson<BaseBean<*>>(mResponse, type)
                return lazyResponse as T
            } else {
                throw ApiException(code, info)
            }
        }
    }
}