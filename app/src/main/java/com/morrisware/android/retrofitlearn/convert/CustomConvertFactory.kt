package com.morrisware.android.retrofitlearn.convert

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by MorrisWare on 2018/10/17.
 * Email: MorrisWare01@gmail.com
 */
class CustomConvertFactory : Converter.Factory() {

    override fun stringConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<*, String>? {
        return StringConvert()
    }

    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        if (type == FormData::class.java) {
            return RequestBodyConvert()
        }
        return null
    }

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        if (type == String::class.java) {
            return ResponseBodyConvert()
        }
        return null
    }

}