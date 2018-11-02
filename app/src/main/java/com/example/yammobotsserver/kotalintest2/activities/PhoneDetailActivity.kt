package com.example.yammobotsserver.kotalintest2.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.yammobotsserver.kotalintest2.R
import com.example.yammobotsserver.kotalintest2.data.vo.PhoneVO
import com.example.yammobotsserver.kotalintest2.mvp.presenter.PhoneDetailPresenter
import com.example.yammobotsserver.kotalintest2.mvp.views.DetailView
import kotlinx.android.synthetic.main.activity_detail.*

class PhoneDetailActivity : AppCompatActivity(),DetailView {


    private val IE_PHONENAME = "phoneName"

    private val TAG: String = this.javaClass.simpleName

    private lateinit var phoneDetailPresenter: PhoneDetailPresenter

    companion object {
        fun getNewIntent(context: Context, phoneName: String): Intent {
            val intent: Intent = Intent(context,PhoneDetailActivity::class.java)
            intent.putExtra("phoneName", phoneName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val phoneName = intent.getStringExtra(IE_PHONENAME)

        phoneDetailPresenter = PhoneDetailPresenter(this)
        phoneDetailPresenter.onFinishUIComponentsSetUp(phoneName)


    }

    override fun displayDetailData(phoneVO: PhoneVO) {
        bindData(phoneVO)
    }

    override fun displayErrorMsg(errorMsg: String?) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT)
    }

    fun bindData(phoneVO: PhoneVO) {

        detail_device_name.setText(phoneVO.deviceName)
        detail_brand.setText(phoneVO.brand)
        detail_technology.setText(phoneVO.technology)
        detail_gprs.setText(phoneVO.gprs)
        detail_announced.setText(phoneVO.announced)
        detail_dimensions.setText(phoneVO.deviceDimensions)
        detail_weight.setText(phoneVO.weight)
        detail_sim.setText(phoneVO.sim)
        detail_type.setText(phoneVO.deviceType)
        detail_size.setText(phoneVO.deviceSize)
        detail_resolution.setText(phoneVO.resolution)
        detail_card_slot.setText(phoneVO.cardSlot)
        detail_wlan.setText(phoneVO.wlan)
        detail_bluetooth.setText(phoneVO.bluetooth)
        detail_gps.setText(phoneVO.gps)
        detail_browser.setText(phoneVO.browser)
        detail_java.setText(phoneVO.java)
        detail_colors.setText(phoneVO.colors)
        detail_cpu.setText(phoneVO.cpu)
        detail__2g_bands.setText(phoneVO._2g_bands)
        detail__3_5mm_jack.setText(phoneVO._3_5mm_jack_)

    }

}