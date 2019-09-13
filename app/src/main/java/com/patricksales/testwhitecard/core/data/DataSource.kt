package com.patricksales.testwhitecard.core.data

import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface DataSource {

    suspend fun getBooksRepositories(page : Int) : Deferred<Response<BookResponse>>
}