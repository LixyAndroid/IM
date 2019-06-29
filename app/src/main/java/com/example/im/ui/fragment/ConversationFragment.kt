package com.example.im.ui.fragment

import com.example.im.R
import kotlinx.android.synthetic.main.header.*

/**
 * @author  Mloong
 * date  2019/6/28 19:26
 */

class ConversationFragment :BaseFragment(){

    override fun getLayoutResId(): Int  = R.layout.fragment_conversation

    override fun init() {
        super.init()

        headerTitle.text = getString(R.string.message)
    }



}