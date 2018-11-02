package com.example.yammobotsserver.kotalintest2.mvp.views

import com.example.yammobotsserver.kotalintest2.data.vo.PhoneVO

interface PhoneListView : BaseView {
    fun displayPhoneListData(phoneVOList : List<PhoneVO>)
    fun launchPhoneDetailData(phoneName: String)
}