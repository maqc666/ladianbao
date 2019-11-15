package com.yifu.ladianbao.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class BaseFragmentAdapter : FragmentPagerAdapter {

    private var fragmentList = arrayListOf<Fragment>()

    private var mTitles = arrayListOf<String>()

    constructor(fm : FragmentManager, fragmentList : ArrayList<Fragment>) : super(fm) {
        this.fragmentList = fragmentList
    }

    constructor(fm : FragmentManager, fragmentList : ArrayList<Fragment>, mTitles : ArrayList<String>) : super(fm) {
        this.mTitles = mTitles
        setFragments(fm, fragmentList, mTitles)
    }

    private fun setFragments(fm : FragmentManager, fragments : ArrayList<Fragment>, mTitles : ArrayList<String>) {

        this.mTitles = mTitles

        val ft = fm.beginTransaction()
        for (f in this.fragmentList) {
            ft?.remove(f)
        }
        ft?.commitAllowingStateLoss()
        fm.executePendingTransactions()

        this.fragmentList = fragments
        notifyDataSetChanged()

    }

    override fun getPageTitle(position : Int) : CharSequence? {
        return mTitles[position]
    }

    override fun getItem(position : Int) : Fragment {
        return fragmentList[position]
    }

    override fun getCount() : Int {
        return fragmentList.size
    }
}