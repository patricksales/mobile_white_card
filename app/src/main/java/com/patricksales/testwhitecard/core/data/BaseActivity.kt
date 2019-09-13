package com.patricksales.testwhitecard.core.data

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity : AppCompatActivity() {

    fun startViewModel(clazz: Class<out BaseViewModel>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(clazz)

    abstract fun getViewModel() : ViewModel?
}