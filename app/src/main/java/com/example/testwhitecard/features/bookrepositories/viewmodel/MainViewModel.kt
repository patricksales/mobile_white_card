package com.example.testwhitecard.features.bookrepositories.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testwhitecard.core.data.BaseViewModel
import com.example.testwhitecard.core.data.Repository
import com.example.testwhitecard.core.data.api.ResponseApi
import com.example.testwhitecard.features.bookrepositories.business.MainBusiness

class MainViewModel(repository: Repository) : BaseViewModel(repository) {

    val business: MainBusiness = MainBusiness(repository)

    val booksLiveData = MutableLiveData<ResponseApi>()

    init {
        initObservables()
    }

    fun initObservables() {
        business?.booksLiveData?.observeForever { responseAPI ->
            booksLiveData.postValue(responseAPI)
        }
    }

    fun getBooks(page: Int) {
        business.start(page)
    }

}
