package com.example.im.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.im.R
import com.example.im.adapter.ContactListAdapter
import com.example.im.contract.ContactContract
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author  Mloong
 * date  2019/6/28 19:26
 */

class ContactFragment :BaseFragment(),ContactContract.View{

    override fun onLoadContactSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter?.notifyDataSetChanged()


    }

    override fun onLoadContactsFailed() {
        swipeRefreshLayout.isRefreshing = false

        context?.toast(R.string.load_contacts_failed)
    }

    override fun getLayoutResId(): Int  = R.layout.fragment_contacts

    override fun init() {
        super.init()

        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter =  ContactListAdapter(context)


        }
    }

}