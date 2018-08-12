package com.sandbox.arch.screen.countries

import android.support.v7.util.DiffUtil
import com.sandbox.arch.model.Country

/**
 * Created by Valentyn on 12.08.2018.
 */
class CountryDiffCallback : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country?, newItem: Country?): Boolean {
        return oldItem?.capital == newItem?.capital
    }

    override fun areContentsTheSame(oldItem: Country?, newItem: Country?): Boolean {
        return oldItem == newItem
    }
}