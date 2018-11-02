package com.example.yammobotsserver.kotalintest2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.yammobotsserver.kotalintest2.R
import com.example.yammobotsserver.kotalintest2.data.vo.PhoneVO
import com.example.yammobotsserver.kotalintest2.delegates.PhoneDelegate
import com.example.yammobotsserver.kotalintest2.viewholders.PhoneViewHolder

class PhoneRVAdapter(context: Context?,phoneDelegate: PhoneDelegate) : BaseRecyclerAdapter<PhoneViewHolder, PhoneVO>(context) {


    private var phoneDelegate: PhoneDelegate
    private var context: Context
    init {
        this.context = context!!
        this.phoneDelegate = phoneDelegate
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): PhoneViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, viewGroup, false)
        return PhoneViewHolder(view, phoneDelegate)    }
}