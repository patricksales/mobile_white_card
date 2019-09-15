package com.patricksales.testwhitecard

import com.patricksales.testwhitecard.core.data.repository.RemoteDataSource
import com.patricksales.testwhitecard.core.data.repository.Repository

object InjectionRepository {

    fun provideRepository(): Repository {
        return Repository.getInstance(RemoteDataSource)
    }
}