package com.sandbox.arch.screen

import android.os.Bundle
import android.view.View
import com.sandbox.arch.R
import com.sandbox.arch.base.BaseActivity
import com.sandbox.arch.screen.countries.CountriesFragment
import com.sandbox.arch.utils.FragmentBackStack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initFragmentBackStack(): FragmentBackStack? {
        mBackstack = FragmentBackStack(supportFragmentManager, R.id.fragmentContainer).apply {
            replace(CountriesFragment.newInstance())
        }

        return mBackstack
    }

    fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

}
