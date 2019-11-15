package com.yifu.ladianbao.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yifu.ladianbao.util.ACache
import com.yifu.ladianbao.util.utilcode.ToastUtils
import org.greenrobot.eventbus.EventBus

abstract class BaseKFragment : Fragment() {

    /**
     * 视图是否加载完毕
     */
    protected var isViewPrepare = false
    protected lateinit var mContext: Context
    /**
     * 数据是否加载过了
     */
    protected var hasLoadData = false
    protected var rootView: View? = null
    protected var aCache: ACache? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
        isViewPrepare = true
        aCache = ACache.get(context)
        mContext = activity as Context
        initView()
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        lazyLoadDataIfPrepared()
    }


    fun showToast(msg: String) {
//        Toasty.normal(act, msg).show()
        ToastUtils.showShort( msg)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化UI
     */
    abstract fun initView()

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            Log.e("BaseFragment", this.javaClass.simpleName)
            lazyLoad()
            hasLoadData = true
        }
    }

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    open fun useEventBus() = false

    @JvmOverloads
    fun gotoActivity(clz: Class<*>, isCloseCurrentActivity: Boolean = false, ex: Bundle? = null) {
        val intent = Intent(context, clz)
        if (ex != null) intent.putExtras(ex)
        startActivity(intent)
        if (isCloseCurrentActivity) {
            (context as Activity).finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }



}