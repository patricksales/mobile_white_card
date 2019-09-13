package com.patricksales.testwhitecard.core.data.api

import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WhiteCardAPI {

    @GET("repositories?q=language:Java&sort=stars")
    fun getRepositoryBook(@Query("page") page : Int) : Deferred<Response<BookResponse>>
}