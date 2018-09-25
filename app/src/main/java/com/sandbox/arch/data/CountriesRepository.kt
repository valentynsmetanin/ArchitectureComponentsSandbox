package com.sandbox.arch.data

import com.sandbox.arch.data.model.Country
import com.sandbox.arch.data.network.Api
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