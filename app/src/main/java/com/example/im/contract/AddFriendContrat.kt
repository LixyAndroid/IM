package com.example.im.contract

/**
 * @author  Mloong
 * date  2019/6/28 23:56
 */
interface AddFriendContrat {

    interface  Presenter : BasePresenter{
        fun  search(key:String)
    }
    interface View{
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}