package com.example.quotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tabs.*

class Tabs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        val pagerAdapter=PagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(MainActivity(),"Quotes")
        pagerAdapter.addFragment(SavedQuotes(),"Collection")

        viewpager.adapter=pagerAdapter
        tabs.setupWithViewPager(viewpager)
    }
}
