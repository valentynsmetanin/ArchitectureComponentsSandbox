package com.sandbox.arch.presentation.base

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.sandbox.arch.utils.FragmentBackStack
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by Valentyn on 28.12.2017.
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    var mBackstack: FragmentBackStack? = null
    var mRetainer: FragmentBackStack.Retainer? = null

    abstract fun initFragmentBackStack(): FragmentBackStack?

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        if (savedInstanceState == null) {
            initRetainBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            initRetainBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mRetainer?.retain(mBackstack)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(ARG_TITLE, title.toString())
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putString(ARG_TITLE, title.toString())
    }

    private fun initRetainBackStack() {
        mRetainer = supportFragmentManager.findFragmentByTag(FragmentBackStack.Retainer::class.java.simpleName) as FragmentBackStack.Retainer?

        // create the retain fragment and back stack the first time
        if (mRetainer == null) {
            initFragmentBackStack()
            // add the retain fragment
            mRetainer = FragmentBackStack.Retainer.newInstance()
            supportFragmentManager.beginTransaction().add(mRetainer, FragmentBackStack.Retainer::class.java.simpleName).commit()

            mRetainer?.retain(mBackstack)
        } else {
            mBackstack = mRetainer?.restore(supportFragmentManager)
            if (mBackstack == null) {
                initFragmentBackStack()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mBackstack?.topFragment?.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val ARG_TITLE : String = "arg_title"
    }

}