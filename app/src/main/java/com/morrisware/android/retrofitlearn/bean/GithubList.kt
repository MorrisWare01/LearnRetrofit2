package com.morrisware.android.retrofitlearn.bean

import com.google.gson.annotations.SerializedName

/**
 * Created by MorrisWare on 2018/10/19.
 * Email: MorrisWare01@gmail.com
 */
data class GithubList<T>(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<T>
)