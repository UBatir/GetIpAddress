package com.example.getip.data

import com.example.getip.data.model.DataIP
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.POST

interface ApiInterface {

    @POST("awstest-service/")
    fun getApi():Observable<DataIP>
}