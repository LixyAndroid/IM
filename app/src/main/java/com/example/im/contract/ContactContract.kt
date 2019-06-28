package com.example.im.contract

/**
 * @author  Mloong
 * date  2019/6/28 21:23
 */
interface ContactContract {

    interface  Presenter :BasePresenter{
        fun loadContacts()
    }

    interface  View{

        fun  onLoadContactSuccess()

        fun  onLoadContactsFailed()


    }
}