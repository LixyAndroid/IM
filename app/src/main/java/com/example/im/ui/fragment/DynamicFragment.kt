package com.example.im.ui.fragment

import com.example.im.R
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*

/**
 * @author  Mloong
 * date  2019/6/28 19:26
 */

class DynamicFragment :BaseFragment(){

    override fun getLayoutResId(): Int  = R.layout.fragment_dynamic

    override fun init() {
        super.init()

        headerTitle.text = getString(R.string.dynamic)

        val logoutString = String.format(getString(R.string.logout),EMClient.getInstance().currentUser)

        logout.text = logoutString
    }

}