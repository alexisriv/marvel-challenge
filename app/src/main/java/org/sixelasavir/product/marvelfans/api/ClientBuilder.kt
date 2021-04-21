package org.sixelasavir.product.marvelfans.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

object ClientBuilder {
    private val gsom by lazy { GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create() }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .addConverterFactory(GsonConverterFactory.create(gsom))
            .build()
    }

    fun <T : Any> service(tClass: KClass<T>): T = retrofit.create(tClass.java)
}
