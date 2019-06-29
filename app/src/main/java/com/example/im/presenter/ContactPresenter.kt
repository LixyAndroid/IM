package com.example.im.presenter

import com.example.im.contract.ContactContract
import com.example.im.data.ContactListItem
import com.example.im.data.db.Contact
import com.example.im.data.db.IMDatebase
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
            //再次加载数据，先清空集合
            contactListItems.clear()

            //清空数据库
            IMDatebase.instance.deleteAllContacts()

            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                //根据首字符排序
                usernames.sortBy { it[0] }

                usernames.forEachIndexed { index, s ->
                    //判断是否显示首字符
                    val showFirstLetter = index == 0 || s[0] != usernames[index - 1][0]


                    val  contactListItem = ContactListItem(s,s[0].toUpperCase(),showFirstLetter)

                    contactListItems.add(contactListItem)

                    val  contact = Contact(mutableMapOf("name" to s))

                    IMDatebase.instance.saveContact(contact)

                }

                uiThread { view.onLoadContactSuccess() }
            }catch (e : HyphenateException){
                uiThread { view.onLoadContactsFailed() }
            }

        }

    }
}