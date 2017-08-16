package com.digital.teo.testdigital.ui.shows

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.digital.teo.testdigital.R
import com.digital.teo.testdigital.network.model.Show
import kotlinx.android.synthetic.main.activity_shows.*
import java.util.ArrayList


class ShowsActivity : AppCompatActivity(), ShowsContract.View {
    var presenter: ShowsContract.Presenter? = null
    var showList: ArrayList<Show> = ArrayList()
    val layManager = LinearLayoutManager(this)
    val scrollListener = object : EndlessRecyclerViewScrollListener(layManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            presenter?.getShowsList(loadNext = totalItemsCount, borderId = showList.last().id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shows)
        presenter = ShowsPresenter()
        presenter?.onCreate(view = this, model = ShowsModel())


        with(rvShowsList) {
            layoutManager = layManager
            adapter = ShowsAdapter(context, showList)
            addItemDecoration(DividerItemDecoration(this@ShowsActivity, R.drawable.custom_recycle_view_divider))
            addOnScrollListener(scrollListener)
        }

        presenter?.getShowsList()

        swipeToRefresh?.setOnRefreshListener {
            presenter?.loadPreviousShowList(borderId = showList.first().id)
        }
    }

    override fun renderShowsInEnd(list: List<Show>) {
        if (list.isNotEmpty()) tvShowsListEmpty.visibility = View.GONE
        showList.addAll(list)
        val recyclerViewState = rvShowsList.layoutManager.onSaveInstanceState()

        rvShowsList.adapter.notifyItemRangeInserted(showList.count(), list.count())
        rvShowsList.visibility = View.VISIBLE
        rvShowsList.layoutManager.onRestoreInstanceState(recyclerViewState)
    }

    override fun renderShowsAtStart(list: List<Show>) {
//        val buff = ArrayList<Show>()
//        buff.addAll(list)
//        buff.addAll(showList)
//        showList = buff

        showList.addAll(0,list)
        rvShowsList.adapter.notifyItemRangeInserted(0, list.count())
        rvShowsList.visibility = View.VISIBLE
    }

    override fun showError(message: String?) {
        rvShowsList.visibility = View.VISIBLE
        tvShowsListEmpty.visibility = View.VISIBLE
        tvShowsListEmpty.text = message
    }

    override fun showMessage(message: String?) =
            Toast.makeText(this, message ?: "Message lost =(", Toast.LENGTH_SHORT).show()

    override fun showProgress(isShown: Boolean) {
        progressBar.visibility = if (isShown) View.VISIBLE else View.GONE
    }

    override fun hideRefresh() {
        swipeToRefresh.isRefreshing = false
    }
}
