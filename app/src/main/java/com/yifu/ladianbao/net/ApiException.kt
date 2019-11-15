package com.yifu.ladianbao.net


class ApiException(code: Int,msg: String): Exception(msg){
    var code: Int? = 0
    init {
        this.code = code
    }
}