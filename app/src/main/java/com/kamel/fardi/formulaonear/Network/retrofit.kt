package com.kamel.fardi.formulaonear.Network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object retrofit {
    private val retrofit: Retrofit? = null// .baseUrl("http://192.168.81.129:9090/")

    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    val instance:
    //////////////////////////////////////////////////////////////
            Retrofit
        get() {
            //////////////////////////////////////////////////////////////
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(25, TimeUnit.SECONDS)
                .readTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .build()
            //////////////////////////////////////////////////////////////
            return Retrofit.Builder()
                .baseUrl("https://formulaonear.onrender.com/") // .baseUrl("http://192.168.81.129:9090/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            //////////////////////////////////////////////////////////////
        }
}
