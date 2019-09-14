package com.patricksales.testwhitecard.core.data

import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import com.patricksales.testwhitecard.features.detailsbook.model.PullRepository
import kotlinx.coroutines.Deferred
import retrofit2.Response

open class Repository(private val dataSource: DataSource) : DataSource {

    override suspend fun getForkRepositories(
        owner: String,
        repository: String
    ): Deferred<Response<List<PullRepository>>> {
        return dataSource.getForkRepositories(owner, repository)
    }

    override suspend fun getBooksRepositories(page: Int): Deferred<Response<BookResponse>> {
        return dataSource.getBooksRepositories(page)
    }

    companion object {

        private var INSTANCE: Repository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: DataSource) =
            INSTANCE
                ?: synchronized(Repository::class.java) {
                    INSTANCE
                        ?: Repository(remoteDataSource)
                            .also { INSTANCE = it }
                }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}