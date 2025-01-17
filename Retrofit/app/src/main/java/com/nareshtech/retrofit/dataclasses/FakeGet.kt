package com.nareshtech.retrofit.dataclasses

// This model class is created to receive the data from the get request.
data class FakeGet(
    var id:Int? = null,
    var title:String? = null,
    var body:String? = null,
    var userId:Int? = null
)