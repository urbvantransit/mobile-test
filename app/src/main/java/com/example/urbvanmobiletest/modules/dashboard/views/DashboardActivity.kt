package com.example.urbvanmobiletest.modules.dashboard.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.urbvanmobiletest.R
import com.example.urbvanmobiletest.modules.dashboard.adapters.ViewPagerAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout

class DashboardActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar

    private lateinit var pager: ViewPager
    private lateinit var adapter: ViewPagerAdapter

    private lateinit var tabs: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initViews()
        initToolbar()
        initViewPager()
    }

    private fun initViews(){
        toolbar = findViewById(R.id.toolbar)
        pager = findViewById(R.id.pager)
        tabs = findViewById(R.id.tabs)
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.toolbar_title)
    }

    private fun initViewPager(){
        val fm = supportFragmentManager
        adapter = ViewPagerAdapter(fm)
        pager.adapter = adapter

    }
}
