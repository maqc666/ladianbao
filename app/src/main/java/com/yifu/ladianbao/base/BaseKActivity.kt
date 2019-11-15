package com.yifu.ladianbao.base

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.gyf.immersionbar.ktx.immersionBar
import com.yifu.ladianbao.R
import com.yifu.ladianbao.util.utilcode.ToastUtils
import com.yifu.ladianbao.wedget.CustomProgressDialog
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.act

abstract class BaseKActivity  : AppCompatActivity(){
    private lateinit var inputMethodManager: InputMethodManager
    protected var dialog1: CustomProgressDialog? = null
    protected var dialog: ProgressDialog? = null
    protected lateinit var mContext: Context
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        mContext = this
        //设置沉浸式状态栏
        if (useImmersionBar()) {
            //状态栏透明
            immersionBar {
            }
        } else {
            //状态栏白色
            immersionBar {
                //只适合纯色状态栏
                fitsSystemWindows(true)
                statusBarColor(R.color.white)
                statusBarDarkFont(true, 0.2f)
                keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
            }
        }
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        dialog1 = CustomProgressDialog(act)
        initView()
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        initData()
        initDialog()
    }

    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    open fun useImmersionBar() = false
    open fun useEventBus() = false

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    fun showToast(msg: String) {
//        Toasty.error(this, msg).show()
//        ToastUtils.getInstance()._short(act, msg)
        ToastUtils.showShort(msg)
    }

    @JvmOverloads
    fun gotoActivity(clz: Class<*>, isCloseCurrentActivity: Boolean = false, ex: Bundle? = null) {
        val intent = Intent(this, clz)
        if (ex != null) intent.putExtras(ex)
        startActivity(intent)
        if (isCloseCurrentActivity) {
            finish()
        }
    }

    protected fun hideSoftKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showSoftKeyboard(view: View) {
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    protected fun initDialog() {
        dialog = ProgressDialog(this)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog?.setMessage("请求网络中...")
    }

    protected fun showDialog() {
        if (dialog != null && !(dialog?.isShowing ?: false))
            dialog?.show()
    }

    protected fun hideDialog() {
        if (dialog != null && dialog?.isShowing ?: false)
            dialog?.dismiss()
    }
}