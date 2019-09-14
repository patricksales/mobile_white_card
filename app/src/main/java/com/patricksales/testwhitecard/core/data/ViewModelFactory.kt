package com.patricksales.testwhitecard.core.data

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.patricksales.testwhitecard.InjectionRepository
import com.patricksales.testwhitecard.features.bookrepositories.viewmodel.MainViewModel
import com.patricksales.testwhitecard.features.detailsbook.viewmodel.DetailBookViewModel

class ViewModelFactory constructor(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(repository)
                isAssignableFrom(DetailBookViewModel::class.java) ->
                    DetailBookViewModel(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T


    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE
                    ?: ViewModelFactory(InjectionRepository.provideRepository())
                        .also { INSTANCE = it }
            }
    }
}