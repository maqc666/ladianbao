package com.yifu.ladianbao.ui.work.popularize

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.widget.TextView
import com.yifu.ladianbao.MessageAdapter
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.ui.CustomFragmentPagerAdapter
import com.yifu.ladianbao.ui.systemmanage.syslist.all.AllFragment
import kotlinx.android.synthetic.main.activity_popular.*
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

class PopularActivity : BaseKActivity() {

    private lateinit var adapter: CustomFragmentPagerAdapter
    private var fragments: Array<Fragment>? = null
    override fun layoutId(): Int {
        return R.layout.activity_popular

    }

    override fun initView() {
        tv_title.text="推广售后"
        image_back.setOnClickListener{
            finish()
        }
        var titles = arrayOf<String>("全部","待审核","已通过","未通过")
        fragments = arrayOf(
               AllFragment.getInstance(0),
                AllFragment.getInstance(1),
                AllFragment.getInstance(2),
                        AllFragment.getInstance(3)
        )
        adapter = CustomFragmentPagerAdapter(fragments, supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 4
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                fragments!![position].onResume()
            }
        })

        tabLayout.setViewPager(viewPager, titles)
        }

    override fun initData() {
         }


}
