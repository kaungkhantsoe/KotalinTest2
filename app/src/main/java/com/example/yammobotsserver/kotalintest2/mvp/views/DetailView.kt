package com.example.yammobotsserver.kotalintest2.mvp.views

import com.example.yammobotsserver.kotalintest2.data.vo.PhoneVO

interface DetailView : BaseView{
    fun displayDetailData(phoneVO: PhoneVO)
}