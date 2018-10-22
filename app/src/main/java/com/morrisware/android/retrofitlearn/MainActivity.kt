package com.morrisware.android.retrofitlearn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.morrisware.android.retrofitlearn.adapter.CustomCallAdapterFactory
import com.morrisware.android.retrofitlearn.api.Api
import com.morrisware.android.retrofitlearn.bean.GithubList
import com.morrisware.android.retrofitlearn.bean.User
import com.morrisware.android.retrofitlearn.convert.CustomConvertFactory
import com.morrisware.android.retrofitlearn.convert.FormData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        call.setOnClickListener { this@MainActivity.call() }
        testErrorGet.setOnClickListener { this@MainActivity.testErrorGet() }
        testPath.setOnClickListener { this@MainActivity.testPath() }
        testStringConverter.setOnClickListener { this@MainActivity.testStringConverter() }
        testRequestBodyConverter.setOnClickListener { this@MainActivity.testRequestBodyConverter() }

        api = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(CustomConvertFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CustomCallAdapterFactory())
            .build()
            .create(Api::class.java)
    }

    private fun call() {
        api.search("MorrisWare01").enqueue(callback)
    }

    private fun testErrorGet() {
        api.errorGetParams("MorrisWare01").enqueue(callback)
    }

    private fun testPath() {
        api.testPath("users", "MorrisWare01").enqueue(callback)
    }

    private fun testStringConverter() {
        api.testStringConverter("MorrisWare01")
            .enqueue(object : com.morrisware.android.retrofitlearn.adapter.Callback {
                override fun onSuccess(value: String) {
                    Log.d("Main", "onSuccess: $value")
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("Main", "onFailure: $throwable")
                }
            })
    }

    private fun testRequestBodyConverter() {
        api.testRequestBodyConverter(FormData())
            .enqueue(object : com.morrisware.android.retrofitlearn.adapter.Callback {
                override fun onSuccess(value: String) {
                    Log.d("Main", "onSuccess: $value")
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("Main", "onFailure: $throwable")
                }
            })
    }

    private val callback = object : Callback<GithubList<User>?> {
        override fun onFailure(call: Call<GithubList<User>?>, t: Throwable) {
            Log.d("Main", "onFailure: ${Thread.currentThread()}")
        }

        override fun onResponse(call: Call<GithubList<User>?>, response: Response<GithubList<User>?>) {
            Log.d("Main", "onResponse: ${response.body()} ${Thread.currentThread()}")
        }
    }
}
