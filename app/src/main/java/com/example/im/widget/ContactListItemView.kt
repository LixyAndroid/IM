package com.example.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im.R
import com.example.im.data.ContactListItem
import kotlinx.android.synthetic.main.view_contact_item.view.*

/**
 * @author  Mloong
 * date  2019/6/28 21:10
 */
class ContactListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    fun bindView(contactListItem: ContactListItem) {
        firstLetter.text = contactListItem.firstLetter.toString()

        userName.text = contactListItem.userName
    }


    init {

        View.inflate(context, R.layout.view_contact_item,this)

    }


}