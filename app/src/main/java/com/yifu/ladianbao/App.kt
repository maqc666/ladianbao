package com.yifu.ladianbao

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.yifu.ladianbao.ui.login.LoginActivity
import com.yifu.ladianbao.util.utilcode.Utils
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.SPCookieStore
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import okhttp3.OkHttpClient
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Level

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        initOkGo()
        Utils.init(this)//初始化项目工具类
        instance=this

    }

    private fun initOkGo() {
        //1. 构建OkHttpClient.Builder
        val builder = OkHttpClient.Builder()
        //2. 配置log
        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.SEVERE)
        builder.addInterceptor(loggingInterceptor)
        //3. 配置超时时间
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        //4. 配置Cookie，以下几种任选其一就行
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(CookieJarImpl(SPCookieStore(this)))

        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE).retryCount = 3                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }
    fun reLogin() {
        //EMClient.getInstance().logout(true)//此方法为同步方法，里面的参数true表示退出登录时解绑GCM或者小米推送的token
        App.instance().finishAllActivity()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    /**
     * 添加Activity到栈
     *
     * @param activity
     */
    fun addActivity(activity: Activity) {
        if (mActivityStack == null) {
            mActivityStack = Stack()
        }
        mActivityStack!!.add(WeakReference(activity))
    }
    /**
     * 检查弱引用是否释放，若释放，则从栈中清理掉该元素
     */
    fun checkWeakReference() {
        if (mActivityStack != null) {
            // 使用迭代器进行安全删除
            val it = mActivityStack!!.iterator()
            while (it.hasNext()) {
                val activityReference = it.next()
                val temp = activityReference.get()
                if (temp == null) {
                    it.remove()
                }
            }
        }
    }
    /**
     * 获取当前Activity（栈中最后一个压入的）
     *
     * @return
     */
    fun currentActivity(): Activity? {
        checkWeakReference()
        return if (mActivityStack != null && !mActivityStack!!.isEmpty()) {
            mActivityStack!!.lastElement().get()
        } else null
    }
    /**
     * 关闭当前Activity（栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = currentActivity()
        if (activity != null) {
            finishActivity(activity)
        }
    }

    /**
     * 关闭指定的Activity
     *
     * @param activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity != null && mActivityStack != null) {
            // 使用迭代器进行安全删除
            val it = mActivityStack!!.iterator()
            while (it.hasNext()) {
                val activityReference = it.next()
                val temp = activityReference.get()
                // 清理掉已经释放的activity
                if (temp == null) {
                    it.remove()
                    continue
                }
                if (temp === activity) {
                    it.remove()
                }
            }
            activity.finish()
        }
    }

    /**
     * 关闭指定的Activity
     *
     * @param activity
     */
    fun removeActivity(activity: Activity?) {
        if (activity != null && mActivityStack != null) {
            // 使用迭代器进行安全删除
            val it = mActivityStack!!.iterator()
            while (it.hasNext()) {
                val activityReference = it.next()
                val temp = activityReference.get()
                // 清理掉已经释放的activity
                if (temp == null) {
                    it.remove()
                    continue
                }
                if (temp === activity) {
                    it.remove()
                }
            }
        }
    }

    /**
     * 关闭指定类名的所有Activity
     *
     * @param cls
     */
    fun finishActivity(cls: Class<*>) {
        if (mActivityStack != null) {
            // 使用迭代器进行安全删除
            val it = mActivityStack!!.iterator()
            while (it.hasNext()) {
                val activityReference = it.next()
                val activity = activityReference.get()
                // 清理掉已经释放的activity
                if (activity == null) {
                    it.remove()
                    continue
                }
                if (activity.javaClass == cls) {
                    it.remove()
                    activity.finish()
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        if (mActivityStack != null) {
            for (activityReference in mActivityStack!!) {
                val activity = activityReference.get()
                if (activity != null) {
                    activity!!.finish()
                }
            }
            mActivityStack!!.clear()
        }
    }
    /**
     * 退出应用程序
     */
    fun exitApp() {
        try {
            finishAllActivity()
            // 退出JVM,释放所占内存资源,0表示正常退出
            System.exit(0)
            // 从系统中kill掉应用程序
            android.os.Process.killProcess(android.os.Process.myPid())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    private var mActivityStack: Stack<WeakReference<Activity>>? = null
    companion object {


        private var instance: App? = null
        fun instance() = instance!!

        //static 代码段可以防止内存泄露
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(android.R.color.transparent, R.color.color_text)//全局设置主题颜色
                ClassicsHeader(context) as RefreshHeader//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }

            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                //指定为经典Footer，默认是 BallPulseFooter
                layout.setPrimaryColorsId(android.R.color.transparent, R.color.color_text)//全局设置主题颜色
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }
    }
}