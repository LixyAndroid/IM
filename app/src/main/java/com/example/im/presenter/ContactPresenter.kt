package com.example.im.presenter

import com.example.im.contract.ContactContract
import com.example.im.data.ContactListItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * @author  Mloong
 * date  2019/6/28 21:29
 */
class ContactPresenter(val  view: ContactContract.View) :ContactContract.Presenter {


    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                //根据首字符排序
                usernames.sortBy { it[0] }

                usernames.forEachIndexed { index, s ->
                    //判断是否显示首字符
                    val showFirstLetter = index == 0 || s[0] != usernames[index - 1][0]


                    val  contactListItem = ContactListItem(s,s[0].toUpperCase(),showFirstLetter)

                    contactListItems.add(contactListItem)
                }



                uiThread { view.onLoadContactSuccess() }
            }catch (e : HyphenateException){
                uiThread { view.onLoadContactsFailed() }
            }

        }

    }
}