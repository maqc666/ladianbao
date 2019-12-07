package com.yifu.ladianbao.util

import android.text.TextUtils
import com.google.gson.Gson
import com.yifu.ladianbao.ui.login.UserBean
import com.yifu.ladianbao.ui.work.popularize.businesspup.CircleBean
import com.yifu.ladianbao.util.utilcode.Utils



object LoginUtils {

    fun getBackground(): String? {
        return PreferencesUtils.getString(Utils.getApp(), "background", "")
    }

    fun saveBackground(background: String){
        PreferencesUtils.putString(Utils.getApp(), "background", background)
    }

    /**
     * 返回用户信息
     * @return
     */
    fun getUser(): UserBean? {
        val json = PreferencesUtils.getString(Utils.getApp(), "user", "")
        return if (TextUtils.isEmpty(json)) null else (Gson().fromJson(json, UserBean::class.java))
    }

    /**
     * 保存用户信息
     * @param userBean
     */
    fun saveUser(userBean: UserBean) {
        val json = Gson().toJson(userBean)
        PreferencesUtils.putString(Utils.getApp(), "user", json)
    }

    /**
     * 保存用户信息
     * 使用版
     */
    fun saveUserInfo(userinfoBean: UserBean?) {
        if(userinfoBean!=null){
            val json = Gson().toJson(userinfoBean)
            PreferencesUtils.putString(Utils.getApp(), "userinfo", json)
        }else{
            PreferencesUtils.putString(Utils.getApp(), "userinfo", "")
        }
    }

    /**
     * 获取用户信息
     * 使用版
     */
    fun getUserInfo(): UserBean? {
        val json = PreferencesUtils.getString(Utils.getApp(), "userinfo", "")
        return if (TextUtils.isEmpty(json)) null else (Gson().fromJson(json, UserBean::class.java))
    }

    /**
     * 判断用户是否登录
     * @return
     */
    fun isLogin(): Boolean {
        val userBean = getUserInfo()
        val token = getToken()
      /*  if (userBean == null || token.isEmpty()) {
            return false
        }*/
        if (userBean==null) {
            return false
        }
        return true
    }

    /**
     * 是否是首次登陆
     */
    fun isFirstLogin(): Boolean {
        return PreferencesUtils.getBoolean(Utils.getApp(), "isfirstlogin", true)
    }

    fun setFirstLogin(isfirst: Boolean) {
        PreferencesUtils.putBoolean(Utils.getApp(), "isfirstlogin", isfirst)
    }

    /**
     * 获取用户token
     * @return String?
     */
    fun getToken(): String {
        return PreferencesUtils.getString(Utils.getApp(), "token", "")
    }

    /**
     * 保存token
     * @param token String
     */
    fun saveToken(token: String) {
        PreferencesUtils.putString(Utils.getApp(), "token", token)
    }

    /**
     * 退出登录
     */
    fun logout() {
        PreferencesUtils.putString(Utils.getApp(), "userinfo", "")
        PreferencesUtils.putString(Utils.getApp(), "token", "")
        PreferencesUtils.putString(Utils.getApp(), "password", "")
//        PreferencesUtils.putString(Utils.getApp(),"userInfo","")
    }

    /**
     * 是否开启定位 1开启 0关闭 默认1
     */
    fun saveLocation(islocation: Int) {
        PreferencesUtils.putInt(Utils.getApp(), "islocation", islocation)
    }

    /**
     * 是否开启定位 1开启 0关闭 默认1
     */
    fun getIsLocation(): Int {
        var isLocation = PreferencesUtils.getInt(Utils.getApp(), "islocation", 1)
        return isLocation
    }

    /**
     * 密码
     */
    fun getUserPassword(): String {
        return PreferencesUtils.getString(Utils.getApp(), "password", "")
    }

    fun setUserPassword(password: String) {
        PreferencesUtils.putString(Utils.getApp(), "password", password)
    }

    fun isFirstOpenApp(): Boolean {
        return PreferencesUtils.getBoolean(Utils.getApp(), "isFirstOpenApp", true)
    }

    fun setFirstOpenApp(isFirstOpenApp: Boolean) {
        PreferencesUtils.putBoolean(Utils.getApp(), "isFirstOpenApp", isFirstOpenApp)
    }

    fun isVoiceAlert(): Boolean {
        return PreferencesUtils.getBoolean(Utils.getApp(), "isVoiceAlert", true)
    }

    fun setVoiceAlert(isVoiceAlert: Boolean) {
        PreferencesUtils.putBoolean(Utils.getApp(), "isVoiceAlert", isVoiceAlert)
    }
}