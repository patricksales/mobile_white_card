package com.patricksales.testwhitecard.features

import androidx.lifecycle.ViewModel
import com.patricksales.testwhitecard.core.data.repository.Repository

open class BaseViewModel(private val repository: Repository? = null) : ViewModel()