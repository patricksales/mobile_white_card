package com.patricksales.testwhitecard.features.detailsbook.viewmodel

import androidx.lifecycle.MutableLiveData
import com.patricksales.testwhitecard.core.data.BaseViewModel
import com.patricksales.testwhitecard.core.data.Repository
import com.patricksales.testwhitecard.core.data.api.ResponseApi
import com.patricksales.testwhitecard.features.detailsbook.business.DetailBookBusiness
import com.patricksales.testwhitecard.features.detailsbook.model.PullRepository

class DetailBookViewModel(repository: Repository) : BaseViewModel(repository) {

    private val business: DetailBookBusiness = DetailBookBusiness(repository)
    private val showError: MutableLiveData<String> = MutableLiveData()
    val repositoryLiveData = MutableLiveData<List<PullRepository>>()

    init {
        initObservables()
    }

    private fun initObservables() {
        business.repositoryFork.observeForever { responseAPI ->
            validateReturn(responseAPI)
        }
    }

    private fun validateReturn(response: ResponseApi?) {
        when (response?.status) {
            ResponseApi.StatusResponse.ERROR -> {
                showError.postValue(response.message)
            }
            ResponseApi.StatusResponse.SUCCESS -> {
                repositoryLiveData.postValue(response.data as List<PullRepository>?)
            }
        }
    }

    fun forkRepositories(author: String, repositoryName: String) {
        business.getPullRequestRepository(author, repositoryName)
    }

}