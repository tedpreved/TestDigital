package com.digital.teo.testdigital.network

import com.digital.teo.testdigital.network.response.ResponseShows
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val ollTvApiClient: ollTvApiInterface
        get() {
            if (sOllTvService == null) {
                val restAdapter = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
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