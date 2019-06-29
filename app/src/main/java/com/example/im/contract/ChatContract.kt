package com.example.im.contract

/**
 * @author  Mloong
 * date  2019/6/29 15:37
 */
interface ChatContract {

    interface Presenter : BasePresenter{
        fun sendMessage(contrat:String,message:String)
    }


    interface  View{
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}