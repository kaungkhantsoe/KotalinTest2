package com.example.yammobotsserver.kotalintest2.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.yammobotsserver.kotalintest2.R
import com.example.yammobotsserver.kotalintest2.RootApp
import com.example.yammobotsserver.kotalintest2.adapters.PhoneRVAdapter
import com.example.yammobotsserver.kotalintest2.data.vo.PhoneVO
import com.example.yammobotsserver.kotalintest2.mvp.presenter.PhoneListPresenter
import com.example.yammobotsserver.kotalintest2.mvp.views.PhoneListView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView



class MainActivity : AppCompatActivity(), PhoneListView{

    private val TAG: String = this.javaClass.simpleName

    private lateinit var mPresenter: PhoneListPresenter
    private lateinit var mAdapter: PhoneRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RootApp().onCreate()

        setContentView(R.layout.activity_main)
        hideSoftKeyboard()

        toolbar.setTitle(R.string.app_name)

        mPresenter = PhoneListPresenter(this,"Samsung")
        mPresenter.onCreate()

        toolbar.setTitle(R.string.app_name)

        rv_main.setHasFixedSize(true)
        rv_main.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))

        mAdapter = PhoneRVAdapter(this, mPresenter)
        rv_main.setAdapter(mAdapter)

        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {

            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {

                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    Log.w(TAG, v.text.toString())
                    mPresenter = PhoneListPresenter(this@MainActivity, v.text.toString())
                    mPresenter.onCreate()
                    hideSoftKeyboard()
                    return true
                }
                return false
            }
        })



    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onResume()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestory()
    }

    override fun displayPhoneListData(phoneVOList: List<PhoneVO>) {
        mAdapter.clearData()
        mAdapter.appendNewData(phoneVOList)
    }

    override fun displayErrorMsg(errorMsg: String?) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show()
    }

    override fun launchPhoneDetailData(phoneName: String) {

        Log.w(TAG, phoneName)
        startActivity(PhoneDetailActivity.getNewIntent(this, phoneName))
    }

    /**
     * Hides the soft keyboard
     */
    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    /**
     * Shows the soft keyboard
     */
    fun showSoftKeyboard(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        inputMethodManager.showSoftInput(view, 0)
    }
}
