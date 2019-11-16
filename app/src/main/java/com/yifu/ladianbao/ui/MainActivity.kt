package com.yifu.ladianbao.ui


import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import android.view.WindowManager
import com.gyf.immersionbar.ktx.immersionBar
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.business.BusinessFragment
import com.yifu.ladianbao.ui.goods.GoodsFragment
import com.yifu.ladianbao.ui.main.MainFragment
import com.yifu.ladianbao.ui.message.MessageFragment
import com.yifu.ladianbao.ui.setting.SettingFragment
import com.yifu.ladianbao.util.utilcode.ToastUtils
import com.yifu.ladianbao.wedget.NativeTabButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseKActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initTab()
        initFragment()
        setFragmentShow(0)
        }

    override fun initData() {
        }


    private fun initTab() {
        mTabButtons = arrayOf(tab0, tab1,tab_center, tab3, tab4)
        for (i in mTabButtons!!.indices) {
            mTabButtons!![i].setTitle(getString(title[i]))
            mTabButtons!![i].setIndex(i)
            mTabButtons!![i].setSelectedImage(ContextCompat.getDrawable(this, checkImage[i]))
            mTabButtons!![i].setUnselectedImage(ContextCompat.getDrawable(this, unCheckImage[i]))
            if (i == 2) {
                mTabButtons!![i].setCenterIcon(true)
            } else {
                mTabButtons!![i].setCenterIcon(false)
            }
        }
    }

    private fun initFragment() {
        mFragments = arrayOf(MainFragment(), GoodsFragment(), BusinessFragment(),MessageFragment(), SettingFragment())
        adapter = CustomFragmentPagerAdapter(mFragments, supportFragmentManager)
        viewPage.adapter = adapter
        viewPage.offscreenPageLimit = 5
        viewPage.setScroll(false)//可切换
        viewPage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                for (item in mTabButtons!!) {
                    item.setSelectedButton(false)
                }
                mTabButtons!![position].setSelectedButton(true)
                //设置顶部状态栏
                if (position == 0 || position == 3) {
                    immersionBar {
                        //只适合纯色状态栏
                        fitsSystemWindows(true)
                        statusBarColor(R.color.color_first)
                        statusBarDarkFont(false, 0.2f)
                        keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                        keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
                    }
                } else {
                    immersionBar {
                        //只适合纯色状态栏
                        fitsSystemWindows(true)
                        statusBarColor(R.color.white)
                        statusBarDarkFont(true, 0.2f)
                        keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                        keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
                    }
                }
            }
        })
    }


    /**
     * 底部按钮点击事件
     */
    fun setFragmentShow(index: Int) {
        viewPage.setCurrentItem(index, false)
        for (item in mTabButtons!!) {
            item.setSelectedButton(false)
        }
        mTabButtons!![index].setSelectedButton(true)
    }

    /**
     * 后退
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtils.showShort("再按一次退出程序")
                exitTime = System.currentTimeMillis()
            } else {
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    private var exitTime: Long = 0
    private var mTabButtons: Array<NativeTabButton>? = null
    private var mFragments: Array<Fragment>? = null
    private var title = intArrayOf(R.string.main_tab0, R.string.main_tab1,R.string.main_tab4,
            R.string.main_tab2, R.string.main_tab3)
    private var checkImage = intArrayOf(R.mipmap.main1, R.mipmap.goods,R.mipmap.pengyouquan, R.mipmap.message,
            R.mipmap.setting)
    private var unCheckImage = intArrayOf(R.mipmap.main_gray, R.mipmap.goods_gray,R.mipmap.pengyouquan, R.mipmap.message_gray,
            R.mipmap.setting_gray)
    private var adapter: CustomFragmentPagerAdapter? = null

}
