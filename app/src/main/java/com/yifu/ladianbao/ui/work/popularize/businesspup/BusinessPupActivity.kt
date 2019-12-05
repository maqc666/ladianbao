package com.yifu.ladianbao.ui.work.popularize.businesspup

import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.login.UserBean
import com.yifu.ladianbao.util.ImageLoader
import com.yifu.ladianbao.util.LoginUtils
import kotlinx.android.synthetic.main.activity_busniss_pup.*

class BusinessPupActivity : BaseKActivity(),BusinessPupContract.View {
    override fun onDataSuccess(bean: CircleBean) {
      LoginUtils.saveCircleBean(bean)


    }

    override fun onDataFail(msg: String) {
        }



    override fun onFail(msg: String) {

    }

    override fun layoutId(): Int {
        return R.layout.activity_busniss_pup
    }

    override fun initView() {
        var image : String
//        ImageLoader.loadImage(
//                this,
//                iv_circle,
//                image
//        )

        }

    override fun initData() {
        mPresenter.getDataList(LoginUtils.getToken())

        }
    val mPresenter by lazy { BusinessPupPresenter(this) }

}
