package com.example.jsonparsing

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient{
    private var BASE_URL:String="https://fcc-weather-api.glitch.me/"
    private var retrofit : Retrofit? =null
    fun getClient():Retrofit{
        if(retrofit==null){
            retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}