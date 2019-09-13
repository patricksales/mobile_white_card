package com.patricksales.testwhitecard.features.bookrepositories.viewmodel

import androidx.lifecycle.MutableLiveData
import com.patricksales.testwhitecard.core.data.BaseViewModel
import com.patricksales.testwhitecard.core.data.Repository
import com.patricksales.testwhitecard.core.data.api.ResponseApi
import com.patricksales.testwhitecard.features.bookrepositories.business.MainBusiness

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
