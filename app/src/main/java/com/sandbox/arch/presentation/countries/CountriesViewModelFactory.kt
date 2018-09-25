package com.sandbox.arch.presentation.countries

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sandbox.arch.data.CountriesRepository
import javax.inject.Inject

/**
 * Created by Valentyn on 9/25/18.
 */
class CountriesViewModelFactory @Inject constructor(
        private val countriesRepository: CountriesRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            return CountriesViewModel(countriesRepository) as T
        }
        throw IllegalArgumentException(String.format("%s Not Found", modelClass.simpleName))
    }

}