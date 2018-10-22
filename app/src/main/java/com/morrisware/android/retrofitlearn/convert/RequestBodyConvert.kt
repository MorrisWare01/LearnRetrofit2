package com.morrisware.android.retrofitlearn.convert

import android.util.Log
import okhttp3.FormBody
import okhttp3.RequestBody
import retrofit2.Converter

/**
 * Created by MorrisWare on 2018/10/22.
 * Email: MorrisWare01@gmail.com
 */
class RequestBodyConvert : Converter<FormData, RequestBody> {

    override fun convert(value: FormData): RequestBody? {
        Log.d("RequestBodyConvert", "RequestBodyConvert $value")
        val formBody = FormBody.Builder()
        for (data in value.map) {
            formBody.add(data.key, data.value)
        }
        return formBody.build()
    }
}