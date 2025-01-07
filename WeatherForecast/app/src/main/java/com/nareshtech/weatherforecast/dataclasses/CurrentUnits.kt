package com.nareshtech.weatherforecast.dataclasses

import com.google.gson.annotations.SerializedName


data class CurrentUnits (

  @SerializedName("time"           ) var time          : String? = null,
  @SerializedName("interval"       ) var interval      : String? = null,
  @SerializedName("temperature_2m" ) var temperature2m : String? = null,
  @SerializedName("wind_speed_10m" ) var windSpeed10m  : String? = null

)