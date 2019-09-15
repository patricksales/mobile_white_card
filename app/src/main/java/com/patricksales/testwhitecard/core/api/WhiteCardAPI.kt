package com.patricksales.testwhitecard.core.api

import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import com.patricksales.testwhitecard.features.detailsbook.model.PullRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WhiteCardAPI {

    @GET("/search/repositories?q=language:Java&sort=stars")
    fun getRepositoryBook(@Query("page") page: Int): Deferred<Response<BookResponse>>

    @GET("/repos/{author}/{repository}/pulls")
    fun getForkRepository(@Path("author") author: String, @Path("repository") repository: String): Deferred<Response<List<PullRepository>>>
}