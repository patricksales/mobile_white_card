package com.patricksales.testwhitecard

import com.patricksales.testwhitecard.core.data.RemoteDataSource
import com.patricksales.testwhitecard.core.data.Repository

object InjectionRepository {

    fun provideRepository(): Repository {
        return Repository.getInstance(RemoteDataSource)
    }
}