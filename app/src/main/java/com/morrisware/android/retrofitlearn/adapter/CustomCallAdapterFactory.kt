package com.morrisware.android.retrofitlearn.adapter

import android.util.Log
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by MorrisWare on 2018/10/22.
 * Email: MorrisWare01@gmail.com
 */
class CustomCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        Log.d("CallAdapter.Factory", "CustomCallAdapterFactory $returnType")
        if (getRawType(returnType) != CustomCall::class.java) {
            return null
        }

        return CustomCallAdapter()
    }

}