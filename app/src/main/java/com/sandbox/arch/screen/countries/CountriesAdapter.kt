package com.sandbox.arch.screen.countries

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sandbox.arch.R
import com.sandbox.arch.model.Country
import kotlinx.android.synthetic.main.item_country.view.*


class CountriesAdapter(private val mListener: OnCountryClicked)
    : ListAdapter<Country, CountriesAdapter.ViewHolder>(CountryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = getItem(position) ?: return

        holder.tvName.text = country.name

        with(holder.itemView) {
            tag = country
            setOnClickListener {
                mListener.onCountryClick(country)
            }
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ivFlag: ImageView = view.iv_flag
        val tvName: TextView = view.tv_name
    }

    interface OnCountryClicked {
        fun onCountryClick(country: Country)
    }

}
