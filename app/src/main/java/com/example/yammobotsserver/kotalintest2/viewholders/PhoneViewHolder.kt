package com.example.yammobotsserver.kotalintest2.viewholders

import android.view.View
import com.example.yammobotsserver.kotalintest2.data.vo.PhoneVO
import com.example.yammobotsserver.kotalintest2.delegates.PhoneDelegate
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class PhoneViewHolder(itemView: View?,mDelegate: PhoneDelegate) : BaseViewHolder<PhoneVO>(itemView) {

    private var mDelegate: PhoneDelegate

    init {
        this.mDelegate = mDelegate
    }
    override fun setData(phoneData: PhoneVO?) {
        mData = phoneData
        itemView.rc_device_name.setText(phoneData?.deviceName)
        itemView.rc_brand.setText(phoneData?.brand)
        itemView.rc_technology.setText(phoneData?.technology)
        itemView.rc_gprs.setText(phoneData?.gprs)
    }

    override fun onClick(v: View?) {
        mDelegate.onTapPhone(mData)
    }
}