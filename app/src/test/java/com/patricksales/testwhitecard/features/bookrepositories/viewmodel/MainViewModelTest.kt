package com.patricksales.testwhitecard.features.bookrepositories.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.patricksales.testwhitecard.core.data.repository.RemoteDataSource
import com.patricksales.testwhitecard.core.data.repository.Repository
import com.patricksales.testwhitecard.core.api.ResponseApi
import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.spy

class MainViewModelTest {

    lateinit var viewModel: MainViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val repository =
            Repository(RemoteDataSource)
        viewModel = spy(MainViewModel(repository))
    }

    @Test
    fun `verifica se é setado corretamente a lista no liveData em caso de sucesso`() {
        val list = listOf(
            BookResponse(incompleteResults = true, items = listOf(), totalCount = 13),
            BookResponse(incompleteResults = false, items = listOf(), totalCount = 21),
            BookResponse(incompleteResults = true, items = listOf(), totalCount = 56),
            BookResponse(incompleteResults = true, items = listOf(), totalCount = 40)
        )
        val responseApi = ResponseApi(
            status = ResponseApi.StatusResponse.SUCCESS,
            message = "OK",
            data = list
        )

        viewModel.validateReturn(responseApi)

        Assert.assertEquals(viewModel.booksLiveData.value, list)
    }

    @Test
    fun `verifica se é setado corretamente a erro no liveData em caso de falha`() {
        val list = listOf<Any>()
        val messageError = "NOK"
        val responseApi = ResponseApi(
            status = ResponseApi.StatusResponse.ERROR,
            message = messageError,
            data = list
        )

        viewModel.validateReturn(responseApi)

        Assert.assertEquals(viewModel.showError.value, messageError)
    }
}