package com.digital.teo.testdigital.ui.shows

import android.util.Log
import com.digital.teo.testdigital.network.response.RxUtils
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Teo on 8/15/17.
 */
class ShowsPresenter : ShowsContract.Presenter {
    var view: ShowsContract.View? = null
    var model: ShowsContract.Model? = null
    var subscription: CompositeDisposable? = null

    override fun onCreate(view: ShowsContract.View, model: ShowsContract.Model) {
        this.view = view
        this.model = model
        subscription = RxUtils.getNewCompositeSubIfUnsubscribed(subscription)
    }

    override fun onDestroy() {
        this.view = null
        this.model = null
        RxUtils.unsubscribeIfNotNull(subscription)
    }

    override fun getShowsList() {
        subscription?.add(model?.getShowsObservable(borderId = "0", direction = 0)
                ?.subscribe(
                        { response ->
                            Log.v("shows",response.toString())
                             view?.renderShows(list = response.items!!)
                        },
                        { error ->
                            error.printStackTrace()
                            error.message?.let { view?.showError(message = it) }
                        }
                ))
    }
}