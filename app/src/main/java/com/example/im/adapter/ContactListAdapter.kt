package com.example.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.im.widget.ContactListItemView

/**
 * @author  Mloong
 * date  2019/6/28 21:15
 */
class ContactListAdapter(val context:Context ):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  ContactListItemViewHolder(ContactListItemView(context))

    }

    override fun getItemCount(): Int  = 30



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class ContactListItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

}