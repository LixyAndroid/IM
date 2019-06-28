package com.example.im.presenter

import com.example.im.contract.ContactContract
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * @author  Mloong
 * date  2019/6/28 21:29
 */
class ContactPresenter(val  view: ContactContract.View) :ContactContract.Presenter {

    override fun loadContacts() {
        doAsync {
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread { view.onLoadContactSuccess() }
            }catch (e : HyphenateException){
                uiThread { view.onLoadContactsFailed() }
            }

        }

    }
}