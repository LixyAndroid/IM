package com.example.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im.R
import com.example.im.contract.LoginContract

/**
 * @author  Mloong
 * date  2019/6/29 15:32
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {


            init {
                View.inflate(context, R.layout.view_receive_message_item,this)
            }

}