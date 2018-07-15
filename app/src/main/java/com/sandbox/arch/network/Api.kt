package com.sandbox.arch.network

import com.sandbox.arch.model.Country
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("all")
    fun getAllCountries(): Observable<List<Country?>>

}