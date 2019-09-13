package com.patricksales.testwhitecard.core.data

import com.patricksales.testwhitecard.core.data.api.WhiteCardService
import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

object RemoteDataSource : DataSource {

    private fun api() = WhiteCardService.whiteCard

    override suspend fun getBooksRepositories(page : Int): Deferred<Response<BookResponse>> {
        return api().getRepositoryBook(page)
    }
}