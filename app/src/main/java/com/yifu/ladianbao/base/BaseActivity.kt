package com.yifu.ladianbao.base

import android.app.ProgressDialog
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.TypedArray
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.gyf.immersionbar.ktx.immersionBar
import com.yifu.ladianbao.App
import com.yifu.ladianbao.R
import com.yifu.ladianbao.util.utilcode.ToastUtils


import org.jetbrains.anko.inputMethodManager

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author         与天同行的观测者
 * @Copyright      Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date           $date$ $time$
 */
abstract class BaseActivity<out P : BasePresent<*>?> : AppCompatActivity() {

    protected val TAG = javaClass.simpleName
    protected var dialog: ProgressDialog? = null
    protected lateinit var mContext: Context
    abstract val mPresenter: P
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        mContext = this
        if (useImmersionBar()) {
            //状态栏透明
            immersionBar {
            }
        } else {
            //状态栏白色
            immersionBar {
                //只适合纯色状态栏
                fitsSystemWindows(true)
                statusBarColor(R.color.color_wait)
                statusBarDarkFont(true, 0.2f)
                keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
            }
        }
        App.instance().addActivity(this)
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating) {
            /**
             * 去掉设置屏幕方向，要不8.0会崩溃
             * Only fullscreen activities can request orientation
             */
        } else {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        initView()
    }

    private fun initView() {
        initLogic()
        setListener()
        processLogic()
        initDialog()
    }

    open fun useImmersionBar() = false
    protected abstract fun initLogic()
    protected abstract fun setListener()
    protected abstract fun processLogic()

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter?.detachView()
        }
    }

    protected fun hideSoftKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showSoftKeyboard(view: View) {
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    protected open fun initDialog() {
        dialog = ProgressDialog(mContext)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog?.setMessage(mContext.getString(R.string.connecting))
    }

    protected fun showDialog() {
        if (dialog != null && !(dialog?.isShowing ?: false))
            dialog?.show()
    }

    protected fun hideDialog() {
        if (dialog != null && dialog?.isShowing ?: false)
            dialog?.dismiss()
    }

    protected fun showToast(msg: String) {
        ToastUtils.showShort(msg)
    }

    val isTranslucentOrFloating: Boolean
        get() {
            var isTranslucentOrFloating = false
            try {
                val styleableRes = Class.forName("com.android.internal.R\$styleable").getField("Window").get(null) as IntArray
                val ta = obtainStyledAttributes(styleableRes)
                val m = ActivityInfo::class.java.getMethod("isTranslucentOrFloating", TypedArray::class.java)
                m.isAccessible = true
                isTranslucentOrFloating = m.invoke(null, ta) as Boolean
                m.isAccessible = false
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return isTranslucentOrFloating
        }

}