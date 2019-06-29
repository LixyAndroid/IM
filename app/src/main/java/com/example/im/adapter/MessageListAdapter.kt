package com.example.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.im.widget.SendMessageItemView
import com.hyphenate.chat.EMMessage

/**
 * @author  Mloong
 * date  2019/6/29 16:27
 */
class MessageListAdapter(val context: Context,val message:List<EMMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{

        val ITEM_TYPE_SEND_MESSAGE = 0
        val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {

        if (message[position].direct() == EMMessage.Direct.SEND){
            return  ITEM_TYPE_SEND_MESSAGE
        }else{
            return  ITEM_TYPE_RECEIVE_MESSAGE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE_SEND_MESSAGE){
            return  SendMessageViewHolder(SendMessageItemView(context))
        }else return  ReceiveMessageViewHolder(SendMessageItemView(context))


    }

    override fun getItemCount(): Int = message.size



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }


    class SendMessageViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    class ReceiveMessageViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }


}