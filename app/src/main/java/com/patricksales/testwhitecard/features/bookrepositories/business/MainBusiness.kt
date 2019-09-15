package com.patricksales.testwhitecard.features.bookrepositories.business

import androidx.lifecycle.MutableLiveData
import com.patricksales.testwhitecard.features.BaseBusiness
import com.patricksales.testwhitecard.core.data.repository.Repository
import com.patricksales.testwhitecard.core.api.ResponseApi
import kotlinx.coroutines.launch

class MainBusiness (
    private val repository: Repository
) : BaseBusiness() {

    val booksLiveData = MutableLiveData<ResponseApi>()

    fun start(page : Int) {
        scope.launch {
            val responseApi = callApi(call = {repository.getBooksRepositories(page).await()})

            booksLiveData.postValue(responseApi)
        }
    }

}