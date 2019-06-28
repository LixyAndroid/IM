package com.example.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.im.widget.AddFriendListItemView

/**
 * @author  Mloong
 * date  2019/6/28 23:48
 */
class AddFriendListAdapter(val  context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return  AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int  = 30



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }


    class AddFriendListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}