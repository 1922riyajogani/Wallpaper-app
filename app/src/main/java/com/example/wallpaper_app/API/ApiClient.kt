package com.example.wallpaper_app.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        val BASE_URL = "https://api.pexels.com/v1/"
        lateinit var retrofit: Retrofit

        fun getApiClient(): Retrofit {


            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit


        }


    }

}