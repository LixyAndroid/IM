package com.example.im.ui.activity

import com.example.im.R
import kotlinx.android.synthetic.main.header.*

/**
 * @author  Mloong
 * date  2019/6/28 23:38
 */
class AddFriendActivity : BaseActivity() {
    override fun getLayoutReId(): Int  = R.layout.activity_add_friend

    override fun init() {
        super.init()

        headerTitle.text = getString(R.string.add_friend)
    }

}