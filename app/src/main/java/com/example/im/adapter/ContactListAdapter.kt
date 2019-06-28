package com.example.im.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.im.R
import com.example.im.data.ContactListItem
import com.example.im.ui.activity.ChatActivity
import com.example.im.widget.ContactListItemView
import org.jetbrains.anko.startActivity

/**
 * @author  Mloong
 * date  2019/6/28 21:15
 */
class ContactListAdapter(val context: Context, val contactListItems: MutableList<ContactListItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  ContactListItemViewHolder(ContactListItemView(context))

    }

    override fun getItemCount(): Int  = contactListItems.size



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val contactListItemView = holder.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[position])
        val  userName = contactListItems[position].userName

        contactListItemView.setOnClickListener { context.startActivity<ChatActivity>("username" to userName) }

        contactListItemView.setOnLongClickListener {
            val message = String.format(context.getString(R.string.delete_friend_message),userName)

            AlertDialog.Builder(context)
                    .setTitle(R.string.delete_friend_title)
                    .setMessage(message)
                    .setNegativeButton(R.string.cancel,null)
                    .setPositiveButton(R.string.confirm,DialogInterface.OnClickListener { dialogInterface, i ->
                        deleteFriend(userName)
                    }).show()
            //返回值
                 true
            }
    }

    private fun deleteFriend(userName: String) {


    }


    class ContactListItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

}