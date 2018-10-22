package com.morrisware.android.retrofitlearn.adapter


/**
 * Created by MorrisWare on 2018/10/22.
 * Email: MorrisWare01@gmail.com
 */
interface CustomCall {

    fun enqueue(callback: Callback)

    fun cancel()

}