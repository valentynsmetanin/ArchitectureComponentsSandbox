package com.sandbox.arch.screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sandbox.arch.R
import com.sandbox.arch.screen.countries.CountriesFragment
import com.sandbox.arch.utils.FragmentBackStack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBackstack: FragmentBackStack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBackstack = FragmentBackStack(supportFragmentManager, R.id.fragmentContainer)
        mBackstack.replace(CountriesFragment.newInstance())
    }

    fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

}
