package com.nareshtech.weatherforecast.dataclasses

import com.google.gson.annotations.SerializedName


data class Current (

  @SerializedName("time"           ) var time          : String? = null,
  @SerializedName("interval"       ) var interval      : Int?    = null,
  @SerializedName("temperature_2m" ) var temperature2m : Double? = null,
  @SerializedName("wind_speed_10m" ) var windSpeed10m  : Double? = null

)