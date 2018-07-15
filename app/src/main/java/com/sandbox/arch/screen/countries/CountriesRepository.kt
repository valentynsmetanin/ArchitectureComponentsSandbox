package com.sandbox.arch.screen.countries

import com.sandbox.arch.model.Country
import com.sandbox.arch.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CountriesRepository(private val api: Api) {

    fun getAllCountries(): Observable<List<Country>> {
        return api.getAllCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.filterNotNull() }
    }

}