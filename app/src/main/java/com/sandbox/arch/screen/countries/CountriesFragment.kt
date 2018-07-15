package com.sandbox.arch.screen.countries

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandbox.arch.R
import com.sandbox.arch.model.Country
import kotlinx.android.synthetic.main.fragment_counties.*


class CountriesFragment : Fragment() {

    private lateinit var mViewModel: CountriesViewModel

    private var mAdapter: CountriesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_counties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        mAdapter = CountriesAdapter(mListener = object : CountriesAdapter.OnCountryClicked {
            override fun onCountryClick(country: Country) {
                // TODO click
            }
        })

        rv_countries?.adapter = mAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(CountriesViewModel::class.java)
        mViewModel.countriesLiveData.observe(this, Observer {
            tv_no_data.visibility = View.GONE
            mAdapter?.mValues = it
            mAdapter?.notifyDataSetChanged()
        })

        mViewModel.getAllCountries()
    }


    companion object {

        @JvmStatic
        fun newInstance() = CountriesFragment()
    }
}
