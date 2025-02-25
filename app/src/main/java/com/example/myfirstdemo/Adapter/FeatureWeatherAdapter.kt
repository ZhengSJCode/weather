package com.example.myfirstdemo.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstdemo.Adapter.FeatureWeatherAdapter.WeatherViewHolder
import com.example.myfirstdemo.R
import com.example.mylibrary.WeatherData

class FeatureWeatherAdapter(
    private val mContext: Context,
    private val weatherDataList: List<WeatherData>?
) : RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        Log.d("FeatureWeatherAdapter","onCreateViewHolder: ")
        val view = LayoutInflater.from(mContext).inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        Log.d("FeatureWeatherAdapter","onBindViewHolder: ")

        val sheshidu =  "℃"
        val weatherData = weatherDataList!![position]
        holder.item1.text = weatherData.wea
        holder.item2.text = weatherData.tem1 +sheshidu +"~" + weatherData.tem2 +sheshidu
        holder.item3.text = weatherData.wind[0] + weatherData.windSpeed
        holder.item4.text = weatherData.air +sheshidu +" " + weatherData.airLevel
        holder.item5.text = weatherData.wea
    }

    override fun getItemCount(): Int {
        return weatherDataList?.size ?: 0
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item1: TextView = itemView.findViewById(R.id.w_i_item1)
        var item2: TextView = itemView.findViewById(R.id.w_i_item2)
        var item3: TextView = itemView.findViewById(R.id.w_i_item3)
        var item4: TextView = itemView.findViewById(R.id.w_i_item4)
        var item5: TextView = itemView.findViewById(R.id.w_i_item5)
    }
}
