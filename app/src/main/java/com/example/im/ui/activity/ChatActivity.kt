package com.example.im.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.im.R
import com.example.im.adapter.EMMessageListenerAdapter
import com.example.im.adapter.MessageListAdapter
import com.example.im.contract.ChatContract
import com.example.im.presenter.ChatPresenter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*


import org.jetbrains.anko.toast

/**
 * @author  Mloong
 * date  2019/6/28 22:20
 */
class ChatActivity:BaseActivity(),ChatContract.View {


    override fun onMessageLoaded() {
        recyclerView.adapter?.notifyDataSetChanged()

        scrollToBottom()
    }

    override fun onMoreMessageLoaded(size: Int) {
        recyclerView.adapter?.notifyDataSetChanged()

        recyclerView.scrollToPosition(size)

    }
    val presenter = ChatPresenter(this)

    lateinit var username :String


    val  messageListener = object :EMMessageListenerAdapter(){

        override fun onMessageReceived(p0: MutableList<EMMessage>?) {

            presenter.addMessage(username,p0)

            runOnUiThread {
                recyclerView.adapter!!.notifyDataSetChanged()
                scrollToBottom()
            }
        }


    }


    override fun onStartSendMessage() {
        //通知RecyclerView刷新列表
        recyclerView.adapter?.notifyDataSetChanged()

    }


    override fun onSendMessageSuccess() {
        recyclerView.adapter?.notifyDataSetChanged()
        toast(R.string.send_message_success)
        //清空编辑框
        edit.text.clear()

        scrollToBottom()

    }

    private fun scrollToBottom() {
        recyclerView.scrollToPosition(presenter.messages.size - 1)
    }

    override fun onSendMessageFailed() {
        toast(R.string.send_message_failed)

        recyclerView.adapter?.notifyDataSetChanged()

    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }




    override fun getLayoutReId(): Int = R.layout.activity_chat

    override fun init() {
        super.init()
        initHeader()
        initEditText()

        initRecyclerView()

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        send.setOnClickListener { send() }


        presenter.loadMessages(username)

    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = MessageListAdapter(context,presenter.messages)


            addOnScrollListener(object :RecyclerView.OnScrollListener(){


                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                    //当RecyclerView是一个空闲状态
                    //检查是否画到顶部，要加载更多数据

                    if (newState == RecyclerView.SCROLL_STATE_IDLE){

                        //如果第一个可见条目的位置是0，为滑到顶部
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0){
                            //加载更多数据

                            presenter.loadMoreMessages(username)


                        }
                    }

                }
            })
        }
    }

    private  fun send(){
        hideSoftKeyboard()
        val message = edit?.text?.trim().toString()
        presenter.sendMessage(username,message)
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

        edit.setOnEditorActionListener { p0, p1, p2 ->
            send()
            true
        }

    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        //获取聊天的用户名
        username = intent.getStringExtra("username")

        val  titleString = String.format(getString(R.string.chat_title),username)

        headerTitle.text = titleString


    }


}