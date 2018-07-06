package com.fernandez.pablo.la24gnc.View.Utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import java.util.ArrayList

/**
 * Created by pablo on 20/02/2017.
 */

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    internal var fragments = ArrayList<Fragment>()
    internal var tabTitles = ArrayList<String>()

    fun addFragments(fragment: Fragment, tabTitle: String) {
        this.fragments.add(fragment)
        this.tabTitles.add(tabTitle)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }
}
