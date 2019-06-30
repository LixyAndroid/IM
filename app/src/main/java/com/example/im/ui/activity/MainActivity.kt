package com.example.im.ui.activity


import com.example.im.R
import com.example.im.adapter.EMMessageListenerAdapter
import com.example.im.factory.FragmentFactory
import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : BaseActivity() {

    val messageListener = object : EMMessageListenerAdapter(){

        override fun onMessageReceived(p0: MutableList<EMMessage>?) {

            runOnUiThread { updateBottomBarUnReadCount() }
        }
    }

    override fun getLayoutReId(): Int = R.layout.activity_main

    override fun init() {

        super.init()

        bottomBar.setOnTabSelectListener {tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(tabId)!!)
            beginTransaction.commit()

        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)

        EMClient.getInstance().addConnectionListener(object : EMConnectionListener{

            override fun onConnected() {


            }

            override fun onDisconnected(p0: Int) {

                if (p0 == EMError.USER_LOGIN_ANOTHER_DEVICE ){

                    //发现又其他设备登录，跳转到登录界面

                    startActivity<LoginActivity>()

                    runOnUiThread {  toast(R.string.user_login_another_device) }

                    finish()

                }
            }

        })
    }

    override fun onResume() {
        super.onResume()

        updateBottomBarUnReadCount()

    }

    private fun updateBottomBarUnReadCount(){

        //初始化bottomBar未读消息的个数

        val tab = bottomBar.getTabWithId(R.id.tab_conversation)

        tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
    }

    override fun onDestroy() {
        super.onDestroy()

        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }

}
