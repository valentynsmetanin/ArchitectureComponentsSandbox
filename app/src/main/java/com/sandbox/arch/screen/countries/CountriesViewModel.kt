package com.sandbox.arch.screen.countries

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sandbox.arch.model.Country
import com.sandbox.arch.network.Api
import com.sandbox.arch.network.ApiClient
import io.reactivex.rxkotlin.subscribeBy

class CountriesViewModel : ViewModel() {

    private var mRepository = CountriesRepository(ApiClient().createService(Api::class.java))

    var countriesLiveData: MutableLiveData<List<Country>> = MutableLiveData()

    fun getAllCountries() {
        mRepository.getAllCountries()
                .subscribeBy(onNext = {
                    countriesLiveData.value = it
                })
    }

}