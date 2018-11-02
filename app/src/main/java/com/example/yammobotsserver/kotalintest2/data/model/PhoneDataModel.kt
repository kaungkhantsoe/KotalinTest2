package com.example.yammobotsserver.kotalintest2.data.model

import android.content.Context
import android.util.Log
import com.example.yammobotsserver.kotalintest2.data.vo.PhoneVO
import com.example.yammobotsserver.kotalintest2.network.PhoneApi
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.TimeUnit

class PhoneDataModel {

    private final val TAG = this.javaClass.simpleName

    private var theAPI: PhoneApi? = null

    private var context: Context

    private val TOKEN: String = "d231c2ad659bb238fbd661c5399269887d80e1562faae63a"

    companion object {
        lateinit var mPhoneMap: HashMap<String, PhoneVO>
        private var INSTANCE: PhoneDataModel? = null

        fun getInstance(): PhoneDataModel {
            if (INSTANCE == null) {
                throw RuntimeException("PhoneDataModel is being invoke before initializing")
            }
            val phoneDataModelObj = INSTANCE
            return phoneDataModelObj!!
        }

        fun initInstance(context: Context) {
            INSTANCE = PhoneDataModel(context)
        }

    }
    init {
        initNewsAPI()
        mPhoneMap = HashMap<String, PhoneVO>()
    }

    constructor(context: Context) {
        this.context = context
    }

    private fun initNewsAPI() {

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()


        val retrofit = Retrofit.Builder()
                .baseUrl("https://fonoapi.freshpixl.com/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        theAPI = retrofit.create(PhoneApi::class.java!!)
    }

    fun startLoadingPhoneData(mNewsSubject: PublishSubject<List<PhoneVO>>, deviceBrand: String) {

        val newsListObservable = theAPI?.getAllPhoneData(TOKEN, deviceBrand)
        newsListObservable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: DisposableObserver<ArrayList<PhoneVO>>() {

                    override fun onNext(phoneVOs: ArrayList<PhoneVO>) {
                        mNewsSubject.onNext(phoneVOs)

                        for (vo in phoneVOs) {

                            mPhoneMap.put(vo.deviceName!!, vo)
                        }
                    }


                    override fun onError(e: Throwable) {
                        Log.w(TAG,e)
                    }

                    override fun onComplete() {

                    }
                })

    }
}