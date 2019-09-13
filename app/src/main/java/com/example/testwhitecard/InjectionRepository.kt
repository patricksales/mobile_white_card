package com.example.testwhitecard

import com.example.testwhitecard.core.data.RemoteDataSource
import com.example.testwhitecard.core.data.Repository

object InjectionRepository {

    fun provideRepository(): Repository {
        return Repository.getInstance(RemoteDataSource)
    }
}