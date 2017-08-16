package com.digital.teo.testdigital.ui.shows

import android.util.Log
import com.digital.teo.testdigital.network.response.ResponseShows
import com.digital.teo.testdigital.network.response.RxUtils
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Teo on 8/15/17
 */
class ShowsPresenter : ShowsContract.Presenter {
    var view: ShowsContract.View? = null
    var model: ShowsContract.Model? = null
    var subscription: CompositeDisposable? = null
    var offset: Int? = null // offset - количество элементов от начала списка до текущего borderId
    var total: Int? = null //total - общее количество элементов
    var hasMore: Int? = null //hasMore - количество элементов от borderId до конца списка

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
        view?.showProgress(isShown = true)
        subscription?.add(model?.getShowsObservable(borderId = "0", direction = 0)
                ?.subscribe(
                        { response ->
                            Log.v("shows", response.toString())
                            view?.renderShowsInEnd(list = response.items!!)
                            offset = response.offset
                            total = response.total
                            hasMore = response.hasMore
                            view?.showProgress(isShown = false)
                        },
                        { error ->
                            error.printStackTrace()
                            error.message?.let {
                                view?.showError(message = it)
                            }
                            view?.showProgress(isShown = false)
                        }
                ))
    }


    override fun getShowsList(loadNext: Int, borderId: String) {
        if (hasMore != 0) {
            view?.showProgress(isShown = true)
            subscription?.add(model?.getShowsObservable(borderId = borderId, direction = 1)
                    ?.subscribe(
                            { response ->
                                Log.v("shows", response.toString())
                                view?.renderShowsInEnd(list = response.items!!)
                                offset = response.offset
                                total = response.total
                                hasMore = response.hasMore
                                logResponse(response)
                                view?.showProgress(isShown = false)
                            },
                            { error ->
                                error.printStackTrace()
                                error.message?.let { view?.showError(message = it) }
                                view?.showProgress(isShown = false)
                            }
                    ))
        }

    }

    override fun loadPreviousShowList(borderId: String) {
        if (offset != 0) {
            subscription?.add(model?.getShowsObservable(borderId = borderId, direction = -1)
                    ?.subscribe(
                            { response ->
                                Log.v("shows", response.toString())
                                view?.renderShowsAtStart(list = response.items!!)
                                offset = response.offset
                                total = response.total
                                hasMore = response.hasMore
                                logResponse(response)
                                view?.hideRefresh()
                            },
                            { error ->
                                error.printStackTrace()
                                error.message?.let { view?.showError(message = it) }
                                view?.hideRefresh()
                            }
                    ))
        }
    }

    fun logResponse(responseShows: ResponseShows) = Log.v("RESPONSE",
            "offset = ${responseShows.offset} \t total = ${responseShows.total}\t hasMore = ${responseShows.hasMore} \t borderId = ${responseShows.items?.last()?.id}")
}