package com.morrisware.android.retrofitlearn.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import java.lang.reflect.Type

/**
 * Created by MorrisWare on 2018/10/22.
 * Email: MorrisWare01@gmail.com
 */
class CustomCallAdapter : CallAdapter<Any, CustomCall> {

    override fun responseType(): Type {
        return String::class.java
    }

    override fun adapt(call: retrofit2.Call<Any>): CustomCall {
        return object : CustomCall {
            override fun enqueue(callback: Callback) {
                call.enqueue(object : retrofit2.Callback<Any?> {
                    override fun onFailure(call: Call<Any?>, t: Throwable) {
                        callback.onFailure(t)
                    }

                    override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                        callback.onSuccess(response.body().toString())
                    }
                })
            }

            override fun cancel() {
                call.cancel()
            }
        }
    }

}