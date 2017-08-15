package com.digital.teo.testdigital.network.ui.shows

import com.digital.teo.testdigital.network.model.Show
import com.digital.teo.testdigital.network.response.ResponseShows
import io.reactivex.Observable

/**
 * Created by Teo on 8/15/17.
 */
interface ShowsContract {
    interface Model {
        fun getShowsObservable(borderId: String, direction: Int): Observable<ResponseShows>?
    }

    interface View {
        fun renderShows(listShows: List<Show>)
        fun showError(message: String)
        fun showMessage(message: String)
    }

    interface Presenter {
        fun onCreate(view: View, model: Model)
        fun onDestroy()
        fun getShowsList()
    }
}