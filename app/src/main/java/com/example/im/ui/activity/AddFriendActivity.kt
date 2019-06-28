package com.example.im.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.im.R
import com.example.im.adapter.AddFriendListAdapter
import com.example.im.contract.AddFriendContrat
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author  Mloong
 * date  2019/6/28 23:38
 */
class AddFriendActivity : BaseActivity(),AddFriendContrat.View {

    override fun onSearchSuccess() {
        dismissProgress()
        toast(R.string.search_success)

        recyclerView.adapter?.notifyDataSetChanged()

    }

    override fun onSearchFailed() {

        dismissProgress()
        toast(R.string.search_failed)
    }

    override fun getLayoutReId(): Int  = R.layout.activity_add_friend

    override fun init() {
        super.init()

        headerTitle.text = getString(R.string.add_friend)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context)
        }
    }




}