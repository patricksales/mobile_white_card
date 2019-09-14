package com.patricksales.testwhitecard.features.detailsbook.business

import androidx.lifecycle.MutableLiveData
import com.patricksales.testwhitecard.core.data.BaseBusiness
import com.patricksales.testwhitecard.core.data.Repository
import com.patricksales.testwhitecard.core.data.api.ResponseApi
import kotlinx.coroutines.launch

class DetailBookBusiness(private val repository: Repository)
    : BaseBusiness() {

    val repositoryFork = MutableLiveData<ResponseApi>()

    fun getPullRequestRepository(author: String, repositoryName: String) {
        scope.launch {
            val responseApi = callApi(call = {repository.getForkRepositories(author, repositoryName).await()})
            repositoryFork.postValue(responseApi)
        }
    }
}