package com.sandbox.arch.data.network

import com.sandbox.arch.data.model.Country
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("all")
    fun getAllCountries(): Observable<List<Country?>>

}