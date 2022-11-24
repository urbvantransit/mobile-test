package com.omargtz.mobiletest.location.view

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.omargtz.mobiletest.R
import com.omargtz.mobiletest.app.App
import com.omargtz.mobiletest.location.view.adapter.ViewPagerAdapter
import com.omargtz.mobiletest.location.view.fragment.TransmitterFragment
import com.omargtz.mobiletest.location.viewmodel.LocationViewModel
import com.omargtz.mobiletest.utils.ViewModelFactory

class LocationActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var viewModel: LocationViewModel
    private lateinit var pagerAdapter: ViewPagerAdapter

    val Context.app: App
        get() = applicationContext as App


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        setViewModel()
        initViews()
        initToolbar()
        //setupMap()
    }

    private fun initViews(){
        toolbar = findViewById(R.id.home_toolbar)

    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.location_title)
    }


    private fun setViewModel(){
        val viewModelFactory = ViewModelFactory(app.LocationRepository)
        viewModel = ViewModelProviders.of(this,viewModelFactory)[LocationViewModel::class.java]

    }



    private fun setupMap(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_content, TransmitterFragment())
            .commit()
    }



}
