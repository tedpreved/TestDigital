package com.digital.teo.testdigital.ui.shows

import com.digital.teo.testdigital.network.ApiClient
import com.digital.teo.testdigital.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Teo on 8/15/17
 */
class ShowsModel : ShowsContract.Model {
    override fun getShowsObservable(borderId: String, direction: Int) =
            ApiClient.ollTvApiClient.getShowsListFromServer(
                    serialNumber = Utils.getMACAddress(interfaceName = "wlan0"),
                    borderId = borderId,
                    direction = direction)
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}