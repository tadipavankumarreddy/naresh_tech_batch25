package com.nareshtech.retrofit

import com.nareshtech.retrofit.dataclasses.FakeGet
import com.nareshtech.retrofit.dataclasses.PostRequest
import com.nareshtech.retrofit.dataclasses.PostResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonPlaceHolderInterface {

    @GET("posts/1")
    fun getData():Call<FakeGet>

    @POST("posts")
    fun postData(@Body postRequest: PostRequest):Call<PostResponse>
}