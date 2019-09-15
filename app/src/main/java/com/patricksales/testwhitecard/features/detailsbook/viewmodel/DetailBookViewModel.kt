package com.patricksales.testwhitecard.features.detailsbook.viewmodel

import androidx.lifecycle.MutableLiveData
import com.patricksales.testwhitecard.features.BaseViewModel
import com.patricksales.testwhitecard.core.data.repository.Repository
import com.patricksales.testwhitecard.core.api.ResponseApi
import com.patricksales.testwhitecard.features.detailsbook.business.DetailBookBusiness
import com.patricksales.testwhitecard.features.detailsbook.model.PullRepository

class DetailBookViewModel(repository: Repository) : BaseViewModel(repository) {

    private val business: DetailBookBusiness = DetailBookBusiness(repository)

    val showError: MutableLiveData<String> = MutableLiveData()
    val repositoryLiveData = MutableLiveData<List<PullRepository>>()

    init {
        initObservables()
    }

    private fun initObservables() {
        business.repositoryFork.observeForever { responseAPI ->
            validateReturn(responseAPI)
        }
    }

    fun validateReturn(response: ResponseApi?) {
        when (response?.status) {
            ResponseApi.StatusResponse.ERROR -> {
                showError.postValue(response.message)
            }
            ResponseApi.StatusResponse.SUCCESS -> {
                val responseData = response.data as? List<PullRepository>?
                responseData?.let {
                    if (it.isEmpty()) {
                        showError.postValue("Não foi possível encontrar nenhum item.")
                    } else {
                        repositoryLiveData.postValue(it)
                    }
                }
            }
        }
    }

    fun forkRepositories(author: String, repositoryName: String) {
        business.getPullRequestRepository(author, repositoryName)
    }

}