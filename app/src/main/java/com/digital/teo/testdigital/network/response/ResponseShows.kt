package com.digital.teo.testdigital.network.response

import com.digital.teo.testdigital.network.model.Show
import com.google.gson.annotations.SerializedName


class ResponseShows {
    @SerializedName("items")
    var items: ArrayList<Show>? = null

    @SerializedName("items_number")
    var itemsNumber: Int? = null

    @SerializedName("total")
    var total: Int? = null

    @SerializedName("offset")
    var offset: Int? = null

    @SerializedName("hasMore")
    var hasMore: Int? = null
}