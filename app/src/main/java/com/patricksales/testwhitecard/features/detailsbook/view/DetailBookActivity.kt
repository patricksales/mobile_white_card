package com.patricksales.testwhitecard.features.detailsbook.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patricksales.testwhitecard.R
import com.patricksales.testwhitecard.core.data.BaseActivity
import com.patricksales.testwhitecard.core.data.BaseViewModel
import com.patricksales.testwhitecard.core.util.Model.KEY_BOOK_INTENT
import com.patricksales.testwhitecard.features.bookrepositories.model.Item
import com.patricksales.testwhitecard.features.detailsbook.adapter.DetailBooksAdapter
import com.patricksales.testwhitecard.features.detailsbook.model.PullRepository
import com.patricksales.testwhitecard.features.detailsbook.viewmodel.DetailBookViewModel
import kotlinx.android.synthetic.main.detail_book_activity.*

class DetailBookActivity : BaseActivity() {

    private var book: Item? = null
    private lateinit var viewModel: DetailBookViewModel

    private lateinit var prAdapter: DetailBooksAdapter
    private lateinit var recyclerViewPR: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private val prList: MutableList<PullRepository>? = mutableListOf()

    override fun getViewModel(): BaseViewModel = startViewModel(DetailBookViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_book_activity)

        viewModel = getViewModel() as DetailBookViewModel

        setupRecyclerView()

        observable()
    }

    private fun setupRecyclerView() {
        recyclerViewPR = findViewById(R.id.rvPR)
        recyclerViewPR.addItemDecoration(DividerItemDecoration(this@DetailBookActivity, DividerItemDecoration.VERTICAL))

        layoutManager = LinearLayoutManager(this@DetailBookActivity)
        recyclerViewPR.layoutManager = layoutManager

        prAdapter = DetailBooksAdapter(prList)
        recyclerViewPR.adapter = prAdapter
    }

    override fun onResume() {
        super.onResume()

        findBook()
    }

    fun observable() {
        viewModel.repositoryLiveData.observe(this, Observer { response ->
            processListRepositoryResponse(response)
        })
    }

    private fun processListRepositoryResponse(repositories: List<PullRepository>) {
        prList?.let {
            it.addAll(repositories)
            prAdapter.notifyDataSetChanged()
        }
    }

    private fun findBook() {
        book = intent.getParcelableExtra(KEY_BOOK_INTENT)

        book?.let { library ->
            library.name?.let { name ->
                toolbar.title = name
                book?.owner?.login?.let { login ->
                    viewModel.forkRepositories(login, name)
                }
            }
        }
    }
}