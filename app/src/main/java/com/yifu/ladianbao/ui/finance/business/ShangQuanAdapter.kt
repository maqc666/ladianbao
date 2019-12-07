package com.yifu.ladianbao.ui.finance.business

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yifu.ladianbao.R
import com.yifu.ladianbao.ui.finance.shangquan.ShangQuanBean

class ShangQuanAdapter : BaseQuickAdapter<BusinessListBean.DataBean, BaseViewHolder>(R.layout.item_business) {
    override fun convert(helper: BaseViewHolder, item: BusinessListBean.DataBean?) {
        var time= helper.getView<TextView>(R.id.tv_time)
        time.text=item?.create_time

        var comment =helper.getView<TextView>(R.id.tv_comment)
        comment.text=item?.remark

        var money =helper.getView<TextView>(R.id.tv_money)
        money.text=item?.money

        var state =helper.getView<TextView>(R.id.tv_state)
        if (item?.sign.equals("1")){
            state.text="成功"

        }else{
            state.text="失败"
        }}

    }

