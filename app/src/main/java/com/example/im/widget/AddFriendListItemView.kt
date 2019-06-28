package com.example.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im.R

/**
 * @author  Mloong
 * date  2019/6/28 23:44
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {


        init {
            View.inflate(context, R.layout.view_add_friend_item,this)
        }
}