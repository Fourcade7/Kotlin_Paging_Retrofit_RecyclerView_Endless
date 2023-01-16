package com.pr7.Kotlin_Paging_Retrofit_RecyclerView_Endless.data.network

import com.pr7.kotlin_pagination.data.network.Api
import com.pr7.kotlin_pagination.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {


    fun getApi(): Api {
        return getRetrofit().create(Api::class.java)
    }



    fun getRetrofit(): Retrofit {
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}