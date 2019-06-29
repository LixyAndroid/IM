package com.example.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*


/**
 * @author  Mloong
 * date  2019/6/29 15:32
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    fun bindView(emMessage: EMMessage) {

        updateMessage(emMessage)
        updateTimestamp(emMessage)

    }


    init {
                View.inflate(context, R.layout.view_receive_message_item,this)
            }



    private fun updateMessage(emMessage: EMMessage) {

        if (emMessage.type == EMMessage.Type.TXT) {
            receiveMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {

            receiveMessage.text = context.getString(R.string.no_text_message)
        }




    }

    //时间戳
    private fun updateTimestamp(emMessage: EMMessage) {

        timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
    }



}