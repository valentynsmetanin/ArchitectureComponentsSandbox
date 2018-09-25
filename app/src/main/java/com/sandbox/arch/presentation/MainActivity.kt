package com.sandbox.arch.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.sandbox.arch.R
import com.sandbox.arch.presentation.base.BaseActivity
import com.sandbox.arch.presentation.countries.CountriesFragment
import com.sandbox.arch.utils.FragmentBackStack
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject



class MainActivity : BaseActivity() {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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
