package com.morrisware.android.retrofitlearn

import com.google.gson.annotations.SerializedName

/**
 * Created by MorrisWare on 2018/10/19.
 * Email: MorrisWare01@gmail.com
 */
data class User(
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val score: Float,
    @SerializedName("site_admin")
    val siteAdmin: Boolean
)