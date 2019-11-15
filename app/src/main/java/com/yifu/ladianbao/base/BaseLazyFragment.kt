package com.yifu.ladianbao.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class BaseLazyFragment<P : BasePresent<*>?> : Fragment() {
    protected val TAG = javaClass.simpleName
    protected lateinit var mContext: Context
    protected var rootView: View? = null
    abstract val mPresenter: P
    abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null)
            rootView = inflater.inflate(layoutRes, null)
        mContext = activity as Context
        isPrepared = true
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        initAll()
        setListener()
        processLogic()
        if (isPrepared && userVisibleHint) {
            loadData(isFirst)
            if (isFirst)
                isFirst = false
        }
    }

    protected abstract fun initAll()
    protected abstract fun setListener()
    protected abstract fun processLogic()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    //是否可见
    protected var isVisble: Boolean = false
    // 标志位，标志Fragment已经初始化完成。
    protected var isPrepared = false
    // 标志位，标志是否第一次加载
    protected var isFirst = true

    /**
     * 使用场景：当fragment结合viewpager使用的时候 这个方法会调用
     * 不会走任何的生命周期 无法通过生命周期进行刷新
     * 可使用setUserVisibleHint来判断
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onVisible()
        } else {
            onInVisible()
        }
    }

    protected fun onInVisible() {}
    protected fun onVisible() {
        if (!isPrepared || !userVisibleHint) {
            return
        }
        loadData(isFirst)
        if (isFirst)
            isFirst = false
    }

    /**
     * 使用场景：使用add hide() show()方法切换fragment
     * 不会走任何的生命周期 无法通过生命周期进行刷新
     * 可使用onHiddenChanged来判断
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            if (!isPrepared || !userVisibleHint) {
                return
            }
            loadData(isFirst)
            if (isFirst)
                isFirst = false
        }
    }

    abstract fun loadData(boolean: Boolean)
}