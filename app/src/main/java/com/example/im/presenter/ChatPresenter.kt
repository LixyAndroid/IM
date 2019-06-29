package com.example.im.presenter

import com.example.im.adapter.EMCallBackAdapter
import com.example.im.contract.ChatContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * @author  Mloong
 * date  2019/6/29 15:46
 */
class ChatPresenter(val view: ChatContract.View):ChatContract.Presenter {

    val messages = mutableListOf<EMMessage>()


    override fun sendMessage(contrat: String, message: String) {

        //创建一条消息
        val emMessage = EMMessage.createTxtSendMessage(message,contrat)

        emMessage?.setMessageStatusCallback(object : EMCallBackAdapter(){

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


    override fun addMessage(username: String, p0: MutableList<EMMessage>?) {

        //加入当前的消息列表
        p0?.let { messages.addAll(it) }

        //更新信息为已读信息
        //获取跟联系人的会话，然后标记会话里面的信息为全部已读

        val conversation = EMClient.getInstance().chatManager().getConversation(username)

        conversation.markAllMessagesAsRead()


    }

    override fun loadMessages(username: String) {

        doAsync {
            val  conversation = EMClient.getInstance().chatManager().getConversation(username)
            messages.addAll(conversation.allMessages)

            uiThread { view.onMessageLoaded() }
        }

    }

}