package com.patricksales.testwhitecard.core.data.api

import com.patricksales.testwhitecard.core.util.API.URL_BASE
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WhiteCardService {

    val whiteCard: WhiteCardAPI = getWhiteCardClient().create(WhiteCardAPI::class.java)

    fun getWhiteCardClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}