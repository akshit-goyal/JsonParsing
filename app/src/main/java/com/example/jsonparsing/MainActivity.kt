package com.example.jsonparsing

import ApiResponse
import android.graphics.drawable.Drawable
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.jsonparsing.Model.Todo
import com.example.jsonparsing.databinding.ActivityMainBinding
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var apiInterface: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        getWeather()


    }

    private fun getWeather() {
        var call :Call<ApiResponse> = apiInterface.getWeather(10,170)
        call.enqueue(object:Callback<ApiResponse>{

            override fun onResponse(call:Call<ApiResponse>, response:Response<ApiResponse>){
                println("Working")
                var main:String = response.body()?.weather?.get(0)?.main.toString()
                settingBackground(main)
                binding.main.text = main
                binding.temp.text = response.body()?.main?.temp.toString()
                binding.bottom.text = "MAX "+response.body()?.main?.temp_max.toString() +" MIN " + response.body()?.main?.temp_min.toString()
               /* binding.constraint.setBackgroundResource(R.mipmap.night_foreground)

                var jsonObject = JSONObject(response.body().toString())
                println(jsonObject.getJSONObject("coord").getString("lon"))*/
            }

            override fun onFailure(call:Call<ApiResponse>,t:Throwable){
                println("error occures ")
            }
        })
    }

    fun settingBackground(main:String){
        when(main){
            "Clouds" -> binding.constraint.setBackgroundResource(R.mipmap.sunny_foreground)
             else -> binding.constraint.setBackgroundResource(R.mipmap.night_foreground)
        }
    }
}