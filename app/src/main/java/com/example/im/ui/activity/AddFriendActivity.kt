package com.example.im.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.im.R
import com.example.im.adapter.AddFriendListAdapter
import com.example.im.contract.AddFriendContrat
import com.example.im.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author  Mloong
 * date  2019/6/28 23:38
 */
class AddFriendActivity : BaseActivity(),AddFriendContrat.View {

    val presenter = AddFriendPresenter(this)

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

        search.setOnClickListener { search() }

        userName.setOnEditorActionListener { p0, p1, p2 ->
            //执行搜索
            search()
            //返回true
            true
        }

    }

    fun search(){
        hideSoftKeyboard()
        showProgress(getString(R.string.searching))
        val key = userName.text.trim().toString()
        presenter.search(key)


    }


}