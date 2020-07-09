package com.example.jsonparsing

import ApiResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.jsonparsing.Model.Todo
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var apiInterface: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        getTodos()

    }

    private fun getTodos() {
        var call :Call<ApiResponse> = apiInterface.getWeather(47,45)
        call.enqueue(object:Callback<ApiResponse>{

            override fun onResponse(call:Call<ApiResponse>, response:Response<ApiResponse>){
                    println("Working")
                println(response.body().toString())
                  //  var jsonObject = JSONObject(response.body().toString())
                    //println(jsonObject.getJSONObject("coord").getString("lon"))
            }

            override fun onFailure(call:Call<ApiResponse>,t:Throwable){
                println("error occures ")
            }
        })
    }
}