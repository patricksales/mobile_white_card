package com.example.testwhitecard.core.data

import androidx.lifecycle.ViewModel

open class BaseViewModel(
    private val repository: Repository? = null) : ViewModel() {

}