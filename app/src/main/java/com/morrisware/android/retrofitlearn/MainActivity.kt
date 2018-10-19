package com.morrisware.android.retrofitlearn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
        api.search("MorrisWare01")
            .enqueue(object : Callback<GithubList<User>?> {
                override fun onFailure(call: Call<GithubList<User>?>, t: Throwable) {
                    Log.d("Main", "$t")
                }

                override fun onResponse(call: Call<GithubList<User>?>, response: Response<GithubList<User>?>) {
                    Log.d("Main", "${response.body()}")
                }
            })
    }

}
