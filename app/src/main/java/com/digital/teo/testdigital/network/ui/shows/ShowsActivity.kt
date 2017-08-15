package com.digital.teo.testdigital.network.ui.shows

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.digital.teo.testdigital.R
import com.digital.teo.testdigital.network.model.Show
import kotlinx.android.synthetic.main.activity_shows.*
import java.util.ArrayList


class ShowsActivity : AppCompatActivity(), ShowsContract.View {
    var presenter: ShowsContract.Presenter? = null
    val showList: ArrayList<Show> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shows)
        presenter = ShowsPresenter()
        presenter?.onCreate(view = this, model = ShowsModel())


        with(rvShowsList) {
            layoutManager = LinearLayoutManager(this@ShowsActivity)
            addItemDecoration(DividerItemDecoration(this@ShowsActivity, R.drawable.custom_recycle_view_divider))
        }

        presenter?.getShowsList()
    }

    override fun renderShows(list: List<Show>) {
        if (list.size != 0) tvShowsListEmpty.visibility = View.GONE
        showList.addAll(list)
        rvShowsList.adapter = ShowsAdapter(baseContext, showList)
        rvShowsList.visibility = View.VISIBLE
    }

    override fun showError(message: String?) {
        rvShowsList.visibility = View.VISIBLE
        tvShowsListEmpty.visibility = View.VISIBLE
        tvShowsListEmpty.text = message
    }

    override fun showMessage(message: String?) =
            Toast.makeText(this, message ?: "Message lost =(", Toast.LENGTH_SHORT).show()


}
