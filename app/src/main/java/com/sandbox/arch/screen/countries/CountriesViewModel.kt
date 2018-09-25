package com.sandbox.arch.screen.countries

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sandbox.arch.model.Country
import com.sandbox.arch.network.Api
import com.sandbox.arch.network.ApiClient
import io.reactivex.rxkotlin.subscribeBy

class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {

    var countriesLiveData: MutableLiveData<List<Country>> = MutableLiveData()
    var errorLiveData: MediatorLiveData<Throwable> = MediatorLiveData()

    init {
        getAllCountries()
    }

    fun getAllCountries() {
        repository.getAllCountries()
                .subscribeBy(
                        onNext = {
                            countriesLiveData.value = it
                        },
                        onError = {
                            errorLiveData.value = it
                        }
                )
    }

}