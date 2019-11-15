package com.yifu.ladianbao.net


data class SimpleResponse (var status: Int,var info: String){

    fun toBaseResponse() : BaseResponse<Void> {
        return BaseResponse(status, info)
    }

}