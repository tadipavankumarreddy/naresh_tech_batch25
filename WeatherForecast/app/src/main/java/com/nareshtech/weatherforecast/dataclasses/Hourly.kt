package com.nareshtech.weatherforecast.dataclasses

import com.google.gson.annotations.SerializedName


data class Hourly (

  @SerializedName("time"                 ) var time               : ArrayList<String> = arrayListOf(),
  @SerializedName("temperature_2m"       ) var temperature2m      : ArrayList<Double>    = arrayListOf(),
  @SerializedName("relative_humidity_2m" ) var relativeHumidity2m : ArrayList<Int>    = arrayListOf(),
  @SerializedName("wind_speed_10m"       ) var windSpeed10m       : ArrayList<Double> = arrayListOf()

)