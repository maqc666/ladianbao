package com.yifu.ladianbao.ui.systemmanage.syslist

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
import com.yifu.ladianbao.ui.systemmanage.syslist.all.AllFragment
import kotlinx.android.synthetic.main.activity_sys_list.*
import kotlinx.android.synthetic.main.layout_titlebar.*
import kotlinx.android.synthetic.main.layout_titlebar.tv_title
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

class SysListActivity : BaseKActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_sys_list
    }

    override fun initView() {
      tv_title.text="系统列表"
        image_back.setOnClickListener{
            finish()
        }
        setView()
        }

    override fun initData() {
         }



    fun setView(){
//        val frag0 = ChatFragment()
//        val bundle0 = Bundle()
//        mFragments.add(frag0)
//        frag0.arguments = bundle0
//
//        val frag3 = ChatFragment()
//        val bundle3 = Bundle()
//        mFragments.add(frag3)
//        frag3.arguments = bundle3
//
//        val frag2 = ChatFragment()
//        val bundle2 = Bundle()
//        mFragments.add(frag2)
//        frag2.arguments = bundle2
//
//        val frag1 = ChatFragment()
//        val bundle1 = Bundle()
//        mFragments.add(frag1)
//        frag1.arguments = bundle1

        for (i in 0 until titleArray.size) {
            val frag = AllFragment()
            val bundle = Bundle()
            bundle.putInt("index", i)
//                bundle.putString("typeId", mTitleDataList[i].cate_id)
            mFragments.add(frag)
            frag.arguments = bundle
        }

//                bundle.putString("typeId", mTitleDataList[i].cate_id)
        adapter= MessageAdapter(supportFragmentManager,mFragments,titleArray)
        viewPager.adapter=adapter
        viewPager.offscreenPageLimit=titleArray.size
        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                viewPager.requestLayout()
            }
        })
        val commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {

            override fun getCount(): Int {
                return titleArray?.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView = CommonPagerTitleView(context)
                colorTransitionPagerTitleView.setContentView(R.layout.item_fragment_message)
                val title = colorTransitionPagerTitleView.findViewById<TextView>(R.id.tv_chat)
                title.text = titleArray[index]
                title.textSize = 14f
                colorTransitionPagerTitleView.onPagerTitleChangeListener = object : CommonPagerTitleView.OnPagerTitleChangeListener {
                    override fun onSelected(p0: Int, p1: Int) {
                        title.setTextColor(ContextCompat.getColor(context, R.color.color_main))

                    }

                    override fun onLeave(p0: Int, p1: Int, p2: Float, p3: Boolean) {

                    }

                    override fun onEnter(p0: Int, p1: Int, p2: Float, p3: Boolean) {

                    }

                    override fun onDeselected(p0: Int, p1: Int) {
                        title.setTextColor(ContextCompat.getColor(context, R.color.color_33))

                    }

                }
                colorTransitionPagerTitleView.setOnClickListener {
                    viewPager.currentItem = index
                }
                return colorTransitionPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.lineWidth = 60f
                indicator.setColors(ContextCompat.getColor(context, R.color.color_main))
                indicator.roundRadius = 5f
                return indicator
            }

        }
        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, viewPager)

    }
    private var adapter: MessageAdapter?=null
    private var mFragments= arrayListOf<Fragment>()

    var titleArray= arrayListOf("全部","运营中","待续费","已停用")
}
