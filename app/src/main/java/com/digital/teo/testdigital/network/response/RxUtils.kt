package com.digital.teo.testdigital.network.response

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Teo on 8/15/17
 */
object RxUtils {
    fun unsubscribeIfNotNull(subscription: Disposable?) {
        subscription?.dispose()
    }

    fun getNewCompositeSubIfUnsubscribed(subscription: CompositeDisposable?): CompositeDisposable {
        if (subscription == null || subscription.isDisposed) {
            return CompositeDisposable()
        }
        return subscription
    }
}