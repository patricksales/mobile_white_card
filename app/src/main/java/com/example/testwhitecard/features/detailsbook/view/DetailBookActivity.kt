package com.example.testwhitecard.features.detailsbook.view

import androidx.lifecycle.ViewModel
import com.example.testwhitecard.core.data.BaseActivity
import com.example.testwhitecard.core.util.Model.KEY_BOOK_INTENT
import com.example.testwhitecard.features.bookrepositories.model.Item

class DetailBookActivity : BaseActivity() {

    private var book : Item? = null

    override fun getViewModel(): ViewModel? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        super.onResume()
    }

    fun startBook() {
        book = intent.getParcelableExtra(KEY_BOOK_INTENT)

        book?.let {

        }
    }
}