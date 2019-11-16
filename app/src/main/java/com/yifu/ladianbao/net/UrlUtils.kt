package com.yifu.ladianbao.net


object UrlUtils {


    /**
     * 正式地址
     */
    // private const val domain = ""//正式地址

    //地址名加 api/
    public const val api_http = "http://ldb.yiqifuo.com/bapi/v1."//正式地址

    /**
     * 接口名
     */
    /***
     * 我的
     */
    //登录
    val login: String = api_http + "login/login"
    //首页
    val storeinfo: String = api_http + "Index/storeinfo"
    //退出
    val loginout: String = api_http + "Index/Loginout"
    //我的-首页








}