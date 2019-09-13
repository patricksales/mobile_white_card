package com.patricksales.testwhitecard.features.detailsbook.view

import androidx.lifecycle.ViewModel
import com.patricksales.testwhitecard.core.data.BaseActivity
import com.patricksales.testwhitecard.core.util.Model.KEY_BOOK_INTENT
import com.patricksales.testwhitecard.features.bookrepositories.model.Item

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