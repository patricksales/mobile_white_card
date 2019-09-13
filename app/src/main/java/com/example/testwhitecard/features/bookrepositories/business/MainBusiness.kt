package com.example.testwhitecard.features.bookrepositories.business

import androidx.lifecycle.MutableLiveData
import com.example.testwhitecard.core.data.BaseBusiness
import com.example.testwhitecard.core.data.Repository
import com.example.testwhitecard.core.data.api.ResponseApi
import kotlinx.coroutines.launch

class MainBusiness (

    private val repository: Repository) : BaseBusiness(repository) {

    val booksLiveData = MutableLiveData<ResponseApi>()

    fun start(page : Int) {
        scope.launch {
            val responseApi = callApi(call = {repository.getBooksRepositories(page).await()})

            booksLiveData.postValue(responseApi)
        }
    }

}