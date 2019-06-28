package com.example.im.presenter

import android.view.View
import com.example.im.contract.SplashContract
import com.hyphenate.chat.EMClient

/**
 * @author  Mloong
 * date  2019/6/27 23:11
 */
class SplashPresenter(val  view: SplashContract.View) : SplashContract.Presenter {

    override fun checkLoginStatus() {

        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean  =
            EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore




}