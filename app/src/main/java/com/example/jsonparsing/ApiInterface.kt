package com.example.jsonparsing

import ApiResponse
import com.example.jsonparsing.Model.Todo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/todos")
    fun gettodo(): Call<ArrayList<Todo>>
    @GET("/api/current")
    fun getWeather(@Query("lon") lon:Int,@Query("lat") lat:Int): Call<ApiResponse>
}