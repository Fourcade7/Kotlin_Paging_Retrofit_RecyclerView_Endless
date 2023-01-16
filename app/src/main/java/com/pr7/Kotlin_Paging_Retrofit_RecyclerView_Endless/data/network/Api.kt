package com.pr7.kotlin_pagination.data.network

import com.pr7.kotlin_pagination.data.model.RickandMorty
import com.pr7.kotlin_pagination.utils.Constants.BASE_URL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("character")
    fun getAlldata(
       @Query("page") page:Int
    ):Call<RickandMorty>

}