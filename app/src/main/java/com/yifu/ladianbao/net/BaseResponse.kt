package com.yifu.ladianbao.net


data class BaseResponse<T>(var code: Int,var msg: String, var data: T? = null)