package com.patricksales.testwhitecard.core.data

import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import com.patricksales.testwhitecard.features.detailsbook.model.PullRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface DataSource {

    suspend fun getBooksRepositories(page: Int): Deferred<Response<BookResponse>>

    suspend fun getForkRepositories(
        owner: String,
        repository: String
    ): Deferred<Response<List<PullRepository>>>
}