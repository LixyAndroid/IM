package com.example.im.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.im.R
import com.example.im.contract.ChatContract
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author  Mloong
 * date  2019/6/28 22:20
 */
class ChatActivity:BaseActivity(),ChatContract.View {

    override fun onStartSendMessage() {
        //通知RecyclerView刷新列表
        recyclerView.adapter?.notifyDataSetChanged()

    }


    override fun onSendMessageSuccess() {
        recyclerView.adapter?.notifyDataSetChanged()
        toast(R.string.send_message_success)
        //清空编辑框
        edit.text.clear()

    }

    override fun onSendMessageFailed() {
        toast(R.string.send_message_failed)

        recyclerView.adapter?.notifyDataSetChanged()

    }

    override fun getLayoutReId(): Int = R.layout.activity_chat

    override fun init() {
        super.init()
        initHeader()
        initEditText()
    }

    private fun initEditText() {

        edit.addTextChangedListener(object :TextWatcher{

            override fun afterTextChanged(p0: Editable?) {
                //如果用户输入的文本长度大于0，发送按钮enable
                send.isEnabled = !p0.isNullOrEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        //获取聊天的用户名
        val username = intent.getStringExtra("username")

        val  titleString = String.format(getString(R.string.chat_title),username)

        headerTitle.text = titleString


    }

}