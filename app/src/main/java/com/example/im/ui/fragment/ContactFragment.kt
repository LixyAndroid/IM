package com.example.im.ui.fragment

import android.view.View
import com.example.im.R
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 * @author  Mloong
 * date  2019/6/28 19:26
 */

class ContactFragment :BaseFragment(){

    override fun getLayoutResId(): Int  = R.layout.fragment_contacts

    override fun init() {
        super.init()

        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
        }
    }

}