package com.yifu.ladianbao.net


object UrlUtils {


    /**
     * 正式地址
     */
    // private const val domain = ""//正式地址

    //地址名加 api/
    public const val api_http = "http://ldb.yiqifuo.com/api/agent."//正式地址

    /**
     * 接口名
     */
    /***
     * 我的
     */
    //登录

    val login: String = api_http + "login/login"
    val circleExtension: String = api_http + "agent/circleExtension"


    val logout: String = api_http + "logout/logout"









}