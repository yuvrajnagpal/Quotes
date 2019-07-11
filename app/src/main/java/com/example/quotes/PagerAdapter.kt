package com.example.quotes

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {

    val fragments=ArrayList<Fragment>()
    val titles=ArrayList<String>()
    override fun getItem(p0: Int): Fragment {

        return fragments!![p0]
    }

    override fun getCount(): Int {

        return fragments!!.size
    }


    fun addFragment(fragment: Fragment,title:String){

        fragments!!.add(fragment)

        titles!!.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles!![position]
    }
}