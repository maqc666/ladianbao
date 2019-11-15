package com.yifu.ladianbao.net


data class BaseResponse<T>(var status: Int,var info: String, var data: T? = null)