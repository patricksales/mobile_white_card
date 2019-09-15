package com.patricksales.testwhitecard.core.data.repository

import com.patricksales.testwhitecard.core.api.WhiteCardService
import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import com.patricksales.testwhitecard.features.detailsbook.model.PullRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response

object RemoteDataSource : DataSource {

    override suspend fun getForkRepositories(
        owner: String,
        repository: String
    ): Deferred<Response<List<PullRepository>>> {
        return api()
            .getForkRepository(owner, repository)
    }

    private fun api() = WhiteCardService.whiteCard

    override suspend fun getBooksRepositories(page : Int): Deferred<Response<BookResponse>> {
        return api().getRepositoryBook(page)
    }
}