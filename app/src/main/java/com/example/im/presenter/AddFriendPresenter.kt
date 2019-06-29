package com.example.im.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.im.contract.AddFriendContrat
import com.example.im.data.AddFriendItem
import com.example.im.data.db.IMDatebase
import com.example.im.ui.activity.AddFriendActivity
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync

/**
 * @author  Mloong
 * date  2019/6/29 11:18
 */
class AddFriendPresenter(val view: AddFriendActivity) : AddFriendContrat.Presenter {

    val addFriendItems = mutableListOf<AddFriendItem>()

    override fun search(key: String) {

        val  query = BmobQuery<BmobUser>()
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>(){

            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) {

                    //处理数据
                    //创建AddFriendItem的集合
                    val allContrats = IMDatebase.instance.getAllContacts()

                    doAsync {
                        p0?.forEach {

                            //比对是否以及添加过好友
                            var isAdded = false

                            for (contact in allContrats){
                                if (contact.name == it.username){

                                    isAdded = true
                                }

                            }

                           val addFriendItem = AddFriendItem(it.username,it.createdAt,isAdded)

                            addFriendItems.add(addFriendItem)




                        }
                        uiThread { view.onSearchSuccess() }

                    }

                    view.onSearchSuccess()

                }
                else view.onSearchFailed()

            }

        })

    }
}