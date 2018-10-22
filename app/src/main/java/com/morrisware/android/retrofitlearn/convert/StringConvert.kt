package com.morrisware.android.retrofitlearn.convert

import android.util.Log
import retrofit2.Converter

/**
 * Created by MorrisWare on 2018/10/22.
 * Email: MorrisWare01@gmail.com
 */
class StringConvert : Converter<Any, String> {

    override fun convert(value: Any): String {
        Log.d("StringConvert", "StringConvert $value")
        return value.toString()
    }
}