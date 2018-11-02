package com.example.yammobotsserver.kotalintest2.network

import com.example.yammobotsserver.kotalintest2.data.vo.PhoneVO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

interface PhoneApi {

    @GET("/v1/getdevice")
    fun getAllPhoneData(@Query("token") token: String, @Query("device") device: String): Observable<ArrayList<PhoneVO>>

}