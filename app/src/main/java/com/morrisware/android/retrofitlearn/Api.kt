package com.morrisware.android.retrofitlearn

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by MorrisWare on 2018/10/17.
 * Email: MorrisWare01@gmail.com
 */
interface Api {

    @GET("search/users")
    fun search(@Query("q") query: String): Call<GithubList<User>>

    /**
     * get请求参数使用@Query
     */
    @GET("search/users?q={q}")
    fun errorGetParams(@Path("q") q: String): Call<GithubList<User>>

    /**
     *
     */
    @GET("search/{path}")
    fun testPath(@Path(value = "path") path: String, @Query("q") q: String): Call<GithubList<User>>


}