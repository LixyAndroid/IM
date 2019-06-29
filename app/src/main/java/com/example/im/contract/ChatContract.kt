package com.example.im.contract

import com.hyphenate.chat.EMMessage

/**
 * @author  Mloong
 * date  2019/6/29 15:37
 */
interface ChatContract {

    interface Presenter : BasePresenter{
        fun sendMessage(contrat:String,message:String)
        fun addMessage(username: String, p0: MutableList<EMMessage>?)
    }


    interface  View{
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}