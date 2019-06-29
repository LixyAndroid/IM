package com.example.im.widget

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody


import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*


/**
 * @author  Mloong
 * date  2019/6/29 15:32
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    fun bindView(emMessage: EMMessage) {
        updateTimestamp(emMessage)

        updateMessage(emMessage)

        updateProgress(emMessage)
    }

    private fun updateMessage(emMessage: EMMessage) {

        if (emMessage.type == EMMessage.Type.TXT){
            sendMessage.text = (emMessage.body as EMTextMessageBody) .message
        }else{

            sendMessage.text = context.getString(R.string.no_text_message)
        }





    }

    private fun updateProgress(emMessage: EMMessage) {

        emMessage.status().let {
            when (it){
                EMMessage.Status.INPROGRESS -> {
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.drawable.send_message_progress)
                    val animationDrawable = sendMessageProgress.drawable as AnimationDrawable
                    animationDrawable.start()

                }

                EMMessage.Status.SUCCESS -> sendMessageProgress.visibility = View.GONE

                EMMessage.Status.FAIL -> {
                    sendMessageProgress.visibility = View.VISIBLE

                    sendMessageProgress.setImageResource(R.mipmap.msg_error)
                }
            }
        }
    }


    //时间戳
    private fun updateTimestamp(emMessage: EMMessage) {

        timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
    }


    init {
                View.inflate(context, R.layout.view_send_message_item,this)
            }

}