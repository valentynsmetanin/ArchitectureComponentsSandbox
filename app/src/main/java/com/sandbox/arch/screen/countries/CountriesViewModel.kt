package com.sandbox.arch.screen.countries

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sandbox.arch.model.Country
import com.sandbox.arch.utils.Event
import com.sandbox.arch.utils.SingleLiveEvent
import io.reactivex.rxkotlin.subscribeBy

class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {

    var countriesLiveData: MutableLiveData<List<Country>> = MutableLiveData()
    var errorLiveData: MutableLiveData<Event<Throwable>> = SingleLiveEvent()

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
                            errorLiveData.value = Event(it)
                        }
                )
    }

}