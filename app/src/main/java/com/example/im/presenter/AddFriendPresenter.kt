package com.example.im.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.im.contract.AddFriendContrat
import com.example.im.ui.activity.AddFriendActivity
import com.hyphenate.chat.EMClient

/**
 * @author  Mloong
 * date  2019/6/29 11:18
 */
class AddFriendPresenter(val view: AddFriendActivity) : AddFriendContrat.Presenter {

    override fun search(key: String) {

        val  query = BmobQuery<BmobUser>()
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>(){

            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) view.onSearchSuccess()

                else view.onSearchFailed()

            }

        })

    }
}