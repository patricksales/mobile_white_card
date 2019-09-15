package com.patricksales.testwhitecard.features.bookrepositories.viewmodel

import androidx.lifecycle.MutableLiveData
import com.patricksales.testwhitecard.core.data.BaseViewModel
import com.patricksales.testwhitecard.core.data.Repository
import com.patricksales.testwhitecard.core.data.api.ResponseApi
import com.patricksales.testwhitecard.features.bookrepositories.business.MainBusiness

class MainViewModel(repository: Repository) : BaseViewModel(repository) {

    private val business: MainBusiness = MainBusiness(repository)

    val booksLiveData = MutableLiveData<Any>()
    val showError: MutableLiveData<String> = MutableLiveData()

    init {
        initObservables()
    }

    private fun initObservables() {
        business.booksLiveData.observeForever { responseAPI ->
            validateReturn(responseAPI)
        }
    }

    fun validateReturn(response: ResponseApi?) {
        when (response?.status) {
            ResponseApi.StatusResponse.ERROR -> {
                showError.postValue(response.message)
            }
            ResponseApi.StatusResponse.SUCCESS -> {
                booksLiveData.postValue(response.data)
            }
        }
    }

    fun getBooks(page: Int) {
        business.start(page)
    }
}
