package com.example.im.presenter

import com.example.im.adapter.EMCallBackAdapter
import com.example.im.contract.ChatContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage

/**
 * @author  Mloong
 * date  2019/6/29 15:46
 */
class ChatPresenter(val view: ChatContract.View):ChatContract.Presenter {

    val messages = mutableListOf<EMMessage>()


    override fun sendMessage(contrat: String, message: String) {

        //创建一条消息
        val emMessage = EMMessage.createTxtSendMessage(message,contrat)

        emMessage.setMessageStatusCallback(object : EMCallBackAdapter(){

            override fun onSuccess() {
                uiThread { view.onSendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onSendMessageFailed() }
            }

        })

        messages.add(emMessage)
        view.onStartSendMessage()

        EMClient.getInstance().chatManager().sendMessage(emMessage)

    }
}