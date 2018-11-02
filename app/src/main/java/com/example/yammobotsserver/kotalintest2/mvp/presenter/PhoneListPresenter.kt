package com.example.yammobotsserver.kotalintest2.mvp.presenter

import com.example.yammobotsserver.kotalintest2.data.model.PhoneDataModel
import com.example.yammobotsserver.kotalintest2.data.vo.PhoneVO
import com.example.yammobotsserver.kotalintest2.delegates.PhoneDelegate
import com.example.yammobotsserver.kotalintest2.mvp.views.PhoneListView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class PhoneListPresenter(mView: PhoneListView?,phoneName: String?) : BasePresenter<PhoneListView>(mView), PhoneDelegate {

    private val phoneName: String

    init {
        this.phoneName = phoneName!!
    }

    private val mPhoneSubject: PublishSubject<List<PhoneVO>> = PublishSubject.create()

    override fun onCreate() {
        super.onCreate()
        mPhoneSubject!!.subscribe(object : Observer<List<PhoneVO>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(baseVOList: List<PhoneVO>) {
                mView.displayPhoneListData(baseVOList)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })

        PhoneDataModel.getInstance().startLoadingPhoneData(mPhoneSubject,phoneName)
    }

    override fun onTapPhone(phone: PhoneVO) {
        mView.launchPhoneDetailData(phone.deviceName!!)
    }
}