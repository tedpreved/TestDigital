package com.digital.teo.testdigital.network.ui.shows

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.digital.teo.testdigital.R


class ShowsActivity : AppCompatActivity() {
    var presenter: ShowsContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shows)

    }
}
