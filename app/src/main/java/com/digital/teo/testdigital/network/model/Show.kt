package com.digital.teo.testdigital.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Teo on 8/15/17
 */
data class Show(@SerializedName("id") val id: String,
                @SerializedName("icon") val urlIcon: String,
                @SerializedName("name") val name: String)

