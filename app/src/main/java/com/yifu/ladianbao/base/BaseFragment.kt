package com.yifu.ladianbao.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

import org.jetbrains.anko.inputMethodManager


abstract class BaseFragment<P : BasePresent<*>> : Fragment() {
    protected val TAG = javaClass.simpleName
    protected lateinit var mContext: Context
    protected var rootView: View? = null
    abstract val mPresenter: P
    abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null)
            rootView = inflater.inflate(layoutRes, null)
        mContext = activity as Context
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
    }

    protected abstract fun initAll()
    protected abstract fun setListener()
    protected abstract fun processLogic()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    protected fun hideSoftKeyboard(view: View) {
        activity?.inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showSoftKeyboard(view: View) {
        activity?.inputMethodManager?.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }
}