package com.morrisware.android.retrofitlearn.convert

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by MorrisWare on 2018/10/22.
 * Email: MorrisWare01@gmail.com
 */
class ResponseBodyConvert : Converter<ResponseBody, String?> {

    override fun convert(value: ResponseBody): String? {
        Log.d("ResponseBodyConvert", "ResponseBodyConvert ${value.string()}")
        return value.string()
    }

}