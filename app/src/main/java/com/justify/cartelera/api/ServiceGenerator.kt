package com.justify.cartelera.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ServiceGenerator will be in charge of instantiating a Retrofit object,
 * applying the Singleton design pattern, this object will make the requests possible.
 * */
object ServiceGenerator {

    private const val URL = "https://my.api.mockaroo.com/"

    private val client = OkHttpClient.Builder().build();

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build();

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
