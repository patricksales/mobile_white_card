package com.patricksales.testwhitecard.features.bookrepositories.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patricksales.testwhitecard.R
import com.patricksales.testwhitecard.core.data.BaseActivity
import com.patricksales.testwhitecard.core.data.BaseViewModel
import com.patricksales.testwhitecard.core.data.api.ResponseApi
import com.patricksales.testwhitecard.features.bookrepositories.model.BookResponse
import com.patricksales.testwhitecard.features.bookrepositories.model.Item
import com.patricksales.testwhitecard.features.bookrepositories.adapter.HomeAdapter
import com.patricksales.testwhitecard.features.bookrepositories.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var homeAdapter: HomeAdapter

    private val bookList: MutableList<Item> = mutableListOf()
    private val mainListBook: MutableList<Item> = mutableListOf()

    private lateinit var recyclerViewBooks: RecyclerView

    private lateinit var layoutManager: LinearLayoutManager

    var isScrolling: Boolean = false


    var getPageBooks: Int = 1

    override fun getViewModel(): BaseViewModel = startViewModel(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = getViewModel() as MainViewModel

        setupRecyclerView()

        initObservables()

        initView()
    }

    private fun setupRecyclerView() {
        recyclerViewBooks = findViewById(R.id.rvBookList)

        layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerViewBooks.layoutManager = layoutManager

        homeAdapter = HomeAdapter(this@MainActivity, bookList)
        recyclerViewBooks.adapter = homeAdapter

        recyclerViewBooks.addOnScrollListener(object : RecyclerView.OnScrollListener() {

/*            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }*/

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    var totalItems = homeAdapter.itemCount

                    if (!isScrolling) {
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
            validateReturn(response)
        })
    }

    private fun validateReturn(response: ResponseApi?) {
        when (response?.status) {
            ResponseApi.StatusResponse.ERROR -> {
                processErrorReturn(response)
            }
            ResponseApi.StatusResponse.SUCCESS -> {
                processSuccessfulReturn(response)
            }
        }
    }

    private fun setVisibility(progress: Int, recycler: Int, error: Int) {
        pbMainLoading.visibility = progress
        //vgMainContainer.visibility = recycler
        vgMainErrorLayout.visibility = error
    }

    private fun processErrorReturn(response: ResponseApi) {
        tvMainErrorMessage.text = response.message

        setVisibility(
            progress = View.GONE,
            recycler = View.GONE,
            error = View.VISIBLE
        )
    }

    private fun processSuccessfulReturn(response: ResponseApi) {
        setVisibility(
            progress = View.GONE,
            recycler = View.VISIBLE,
            error = View.GONE
        )
        pbMainLoading.visibility = View.GONE

        val bookResponse = response.data as? BookResponse
        val booksList = bookResponse?.items

        mainListBook.clear()
        booksList?.let {
            mainListBook.addAll(it)
            dataSetWasChanged(it)
        }
    }

    private fun dataSetWasChanged(list: List<Item>) {
        bookList.clear()
        bookList.addAll(list)
        homeAdapter.notifyDataSetChanged()
    }

    fun initView() {
        viewModel.getBooks(getPageBooks)
    }
}
