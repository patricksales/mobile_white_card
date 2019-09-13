package com.patricksales.testwhitecard.core.data

import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

open class Repository(private val dataSource: DataSource) : DataSource {

    override suspend fun getBooksRepositories(page : Int): Deferred<Response<BookResponse>> {
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