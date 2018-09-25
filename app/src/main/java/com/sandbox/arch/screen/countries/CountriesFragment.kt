package com.sandbox.arch.screen.countries

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sandbox.arch.ArchSandboxApplication
import com.sandbox.arch.R
import com.sandbox.arch.di.countries.CountriesModule
import com.sandbox.arch.model.Country
import kotlinx.android.synthetic.main.fragment_counties.*
import javax.inject.Inject


class CountriesFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: CountriesViewModelFactory

    private lateinit var mViewModel: CountriesViewModel

    private var mAdapter: CountriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        ArchSandboxApplication.appComponent.plus(CountriesModule()).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_counties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CountriesViewModel::class.java)
        mViewModel.countriesLiveData.observe(this, Observer {
            if (it?.isNotEmpty() == true) {
                tv_no_data.visibility = View.GONE
                rv_countries.visibility = View.VISIBLE
                mAdapter?.submitList(it)
            } else {
                rv_countries.visibility = View.GONE
                tv_no_data.visibility = View.VISIBLE
            }

        })

        mViewModel.errorLiveData.observe(this, Observer { event ->
            event?.getContentIfNotHandled()?.let {
                Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecycler() {
        mAdapter = CountriesAdapter(object : CountriesAdapter.OnCountryClicked {
            override fun onCountryClick(country: Country) {
                // TODO click
            }
        })

        rv_countries?.adapter = mAdapter
    }


    companion object {

        @JvmStatic
        fun newInstance() = CountriesFragment()
    }
}
