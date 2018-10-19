package com.morrisware.android.retrofitlearn

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by MorrisWare on 2018/10/17.
 * Email: MorrisWare01@gmail.com
 */
interface Api {

    @GET("search/users")
    fun search(@Query("q") query: String): Call<GithubList<User>>

}