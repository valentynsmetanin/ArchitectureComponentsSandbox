package com.sandbox.arch.screen.countries

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sandbox.arch.R
import com.sandbox.arch.model.Country
import kotlinx.android.synthetic.main.item_country.view.*


class CountriesAdapter(
        var mValues: List<Country>? = null,
        private val mListener: OnCountryClicked)
    : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = mValues?.get(position) ?: return

        holder.tvName.text = country.name

        with(holder.mView) {
            tag = country
            setOnClickListener {
                mListener.onCountryClick(country)
            }
        }
    }

    override fun getItemCount(): Int = mValues?.size ?: 0

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val ivFlag: ImageView = mView.iv_flag
        val tvName: TextView = mView.tv_name
    }

    interface OnCountryClicked {
        fun onCountryClick(country: Country)
    }

}
