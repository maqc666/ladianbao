package com.yifu.ladianbao.ui.work.popularize.businesspup

import android.text.TextUtils
import android.widget.Toast
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.util.ImageLoader
import com.yifu.ladianbao.util.LoginUtils
import kotlinx.android.synthetic.main.activity_busniss_pup.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*

class BusinessPupActivity : BaseKActivity(),BusinessPupContract.View {


    override fun onDataSuccess(bean: CircleBean) {
        if (TextUtils.isEmpty(bean.background_img)||TextUtils.isEmpty(bean.qr_code)){
            Toast.makeText(this,"background is null",Toast.LENGTH_SHORT).show()
        }
        ImageLoader.loadImage(this,iv_circle,bean.background_img)
        ImageLoader.loadImage(this,iv_qr,bean.qr_code)
//        Toast.makeText(this,bean.qr_code,Toast.LENGTH_SHORT).show()
    }

    override fun onDataFail(msg: String) {
        }



    override fun onFail(msg: String) {

    }

    override fun layoutId(): Int {
        return R.layout.activity_busniss_pup
    }

    override fun initView() {
    tv_title.text="商圈推广"
    image_back.setOnClickListener { finish() }
        mPresenter.attachView(this)




        }

    override fun initData() {
        mPresenter.getDataList(LoginUtils.getToken())

        }
    val mPresenter by lazy { BusinessPupPresenter(this) }

}
