package com.nareshtech.weatherforecast.dataclasses

import com.google.gson.annotations.SerializedName


data class HourlyUnits (

  @SerializedName("time"                 ) var time               : String? = null,
  @SerializedName("temperature_2m"       ) var temperature2m      : String? = null,
  @SerializedName("relative_humidity_2m" ) var relativeHumidity2m : String? = null,
  @SerializedName("wind_speed_10m"       ) var windSpeed10m       : String? = null

)