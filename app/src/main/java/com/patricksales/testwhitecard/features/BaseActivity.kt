package com.patricksales.testwhitecard.features

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.patricksales.testwhitecard.core.data.ViewModelFactory

abstract class BaseActivity : AppCompatActivity() {

    fun startViewModel(clazz: Class<out BaseViewModel>) =
        ViewModelProviders.of(this,
            ViewModelFactory.getInstance()
        ).get(clazz)

    abstract fun getViewModel() : ViewModel?
}