package com.morrisware.android.retrofitlearn.adapter

/**
 * Created by MorrisWare on 2018/10/22.
 * Email: MorrisWare01@gmail.com
 */
interface Callback {

    fun onSuccess(value: String)

    fun onFailure(throwable: Throwable)
    
}