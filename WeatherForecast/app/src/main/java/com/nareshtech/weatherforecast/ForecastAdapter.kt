package com.nareshtech.weatherforecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nareshtech.weatherforecast.dataclasses.Hourly

class ForecastAdapter(private val Context: Context, private val hourly: Hourly):Adapter<ForecastAdapter.ForecastViewHolder>() {
    inner class ForecastViewHolder(itemView: View):ViewHolder(itemView) {
        val dateAndTime = itemView.findViewById<TextView>(R.id.date_and_time)
        val temperature2mTv = itemView.findViewById<TextView>(R.id.temperature_2m_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val v = LayoutInflater.from(Context).inflate(R.layout.one_item_design,parent,false)
        return ForecastViewHolder(v)
    }

    override fun getItemCount(): Int {
        return hourly.time.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val time = hourly.time[position]
        val date = time.split("T").joinToString("\n")
        holder.dateAndTime.text = date
        holder.temperature2mTv.text = "${hourly.temperature2m[position].toString()}°c"
    }
}