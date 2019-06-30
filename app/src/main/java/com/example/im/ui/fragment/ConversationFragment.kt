package com.example.im.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.im.R
import com.example.im.adapter.ConversionListAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * @author  Mloong
 * date  2019/6/28 19:26
 */

class ConversationFragment :BaseFragment(){

    val conversations = mutableListOf<EMConversation>()

    override fun getLayoutResId(): Int  = R.layout.fragment_conversation

    override fun init() {
        super.init()

        headerTitle.text = getString(R.string.message)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversionListAdapter(context,conversations)
        }


        loadConversations()

    }

    private fun loadConversations() {

        doAsync {

            val allConversations = EMClient.getInstance().chatManager().allConversations

            conversations.addAll(allConversations.values)

            uiThread {
                recyclerView.adapter?.notifyDataSetChanged()
            }

        }

    }


}