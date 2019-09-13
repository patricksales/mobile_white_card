package com.example.testwhitecard.core.data

import com.example.testwhitecard.core.data.api.WhiteCardService
import com.example.testwhitecard.features.bookrepositories.model.BookResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

object RemoteDataSource : DataSource {

    private fun api() = WhiteCardService.whiteCard

    override suspend fun getBooksRepositories(page : Int): Deferred<Response<BookResponse>> {
        return api().getRepositoryBook(page)
    }
}