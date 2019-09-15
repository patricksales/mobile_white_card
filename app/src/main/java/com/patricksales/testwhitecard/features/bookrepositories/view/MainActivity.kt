package com.patricksales.testwhitecard.features.bookrepositories.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patricksales.testwhitecard.R
import com.patricksales.testwhitecard.features.BaseActivity
import com.patricksales.testwhitecard.features.BaseViewModel
import com.patricksales.testwhitecard.features.bookrepositories.adapter.HomeAdapter
import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import com.patricksales.testwhitecard.features.bookrepositories.model.Item
import com.patricksales.testwhitecard.features.bookrepositories.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var recyclerViewBooks: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private val bookList: MutableList<Item>? = mutableListOf()

    var isLoading: Boolean = false
    var getPageBooks: Int = 1

    override fun getViewModel(): BaseViewModel = startViewModel(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = getViewModel() as MainViewModel

        setupRecyclerView()

        setListener()

        initObservables()

        getBooks()
    }

    private fun setListener() {
        btMainRetry.setOnClickListener {
            doRetry()
        }

        recyclerViewBooks.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    var totalItems = homeAdapter.itemCount

                    if (!isLoading) {
                        if ((visibleItemCount + pastVisibleItem) >= totalItems) {
                            getPageBooks++
                            viewModel.getBooks(getPageBooks)
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun initObservables() {
        viewModel.booksLiveData.observe(this, Observer { response ->
            processSuccessfulReturn(response)
        })

        viewModel.showError.observe(this, Observer { message ->
            showError(message)
        })
    }

    private fun setupRecyclerView() {
        recyclerViewBooks = findViewById(R.id.rvBookList)

        layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerViewBooks.layoutManager = layoutManager

        homeAdapter = HomeAdapter(this@MainActivity, bookList)
        recyclerViewBooks.adapter = homeAdapter
    }

    private fun setVisibility(progress: Int, recycler: Int, error: Int) {
        vgErrorAPI.visibility = error
        pbLoading.visibility = progress
        vgMainContainer.visibility = recycler
    }

    private fun showError(message: String) {
        tvMainErrorMessage.text = message

        setVisibility(
            progress = View.GONE,
            recycler = View.GONE,
            error = View.VISIBLE
        )
    }

    private fun processSuccessfulReturn(response: Any?) {
        setVisibility(
            progress = View.GONE,
            recycler = View.VISIBLE,
            error = View.GONE
        )
        pbLoading.visibility = View.GONE

        val booksList = (response as? BookResponse)?.items

        booksList?.let {
            dataSetWasChanged(it as List<Item>)
        }
    }

    private fun dataSetWasChanged(list: List<Item>) {
        bookList?.let {
            it.addAll(list)
            homeAdapter.notifyDataSetChanged()
        }
    }

    private fun getBooks() {
        isLoading = true
        viewModel.getBooks(getPageBooks)
        setVisibility(
            progress = View.VISIBLE,
            recycler = View.GONE,
            error = View.GONE
        )
    }

    private fun doRetry() {
        getBooks()
    }
}
