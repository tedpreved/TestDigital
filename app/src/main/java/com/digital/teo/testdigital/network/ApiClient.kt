package com.digital.teo.testdigital.network

import com.digital.teo.testdigital.network.response.ResponseShows
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Teo on 8/15/17
 */
object ApiClient {
    private var sOllTvService: ollTvApiInterface? = null
    val BASE_URL = "http://oll.tv/"

    val ollTvApiClient: ollTvApiInterface
        get() {
            if (sOllTvService == null) {
                val restAdapter = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()

                sOllTvService = restAdapter.create(ollTvApiInterface::class.java)
            }

            return sOllTvService!!
        }

    interface ollTvApiInterface {
        @GET("demo?")
        fun getShowsListFromServer(@Query("serial_number") serialNumber: String,
                                   @Query("borderId") borderId: String,
                                   @Query("direction") direction: Int): Observable<ResponseShows>
    }
}