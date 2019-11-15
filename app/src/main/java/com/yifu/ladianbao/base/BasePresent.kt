package com.yifu.ladianbao.base

import android.content.Context
import com.lzy.okgo.OkGo

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author         与天同行的观测者
 * @Copyright      Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date           $date$ $time$
 */
abstract class BasePresent<V : BaseView> {
    var mView: V? = null
    var mContext: Context? = null
    open fun attachView(view: V) {
        mView = view
        mContext = mView?.getContext()
    }

    open fun detachView() {
        OkGo.getInstance().cancelTag(mView)
        mView = null
    }
}