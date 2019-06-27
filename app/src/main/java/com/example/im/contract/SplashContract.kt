package com.example.im.contract

/**
 * @author  Mloong
 * date  2019/6/27 22:59
 */
interface SplashContract {

    interface  Presenter:BasePresenter{
        fun checkLoginStatus()
    }


    interface  View{
        fun  onNotLoggedIn() //没有登录的ui处理
        fun  onLoggedIn() //已经登录的的ui处理
    }
}