package com.morrisware.android.retrofitlearn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        testErrorGet.setOnClickListener { this@MainActivity.testErrorGet() }
        testPath.setOnClickListener { this@MainActivity.testPath() }
        api = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        api.search("MorrisWare01").enqueue(callback)
    }

    private fun testErrorGet() {
        api.errorGetParams("MorrisWare01").enqueue(callback)
    }

    private fun testPath() {
        api.testPath("users", "MorrisWare01").enqueue(callback)
    }

    private val callback = object : Callback<GithubList<User>?> {
        override fun onFailure(call: Call<GithubList<User>?>, t: Throwable) {
            Log.d("Main", "onFailure: ${Thread.currentThread().name}")
        }

        override fun onResponse(call: Call<GithubList<User>?>, response: Response<GithubList<User>?>) {
            Log.d("Main", "onResponse: ${Thread.currentThread().name}")
        }
    }
}
