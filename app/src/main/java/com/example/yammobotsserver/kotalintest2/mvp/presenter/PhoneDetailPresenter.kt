package com.example.yammobotsserver.kotalintest2.mvp.presenter

import com.example.yammobotsserver.kotalintest2.data.model.PhoneDataModel
import com.example.yammobotsserver.kotalintest2.mvp.views.DetailView

class PhoneDetailPresenter(mView: DetailView?) : BasePresenter<DetailView>(mView) {

    fun onFinishUIComponentsSetUp(phoneName: String) {
        val phoneVO = PhoneDataModel.mPhoneMap[phoneName]
        mView.displayDetailData(phoneVO!!)
    }
}