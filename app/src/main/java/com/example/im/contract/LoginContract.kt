package com.example.im.contract

/**
 * @author  Mloong
 * date  2019/6/28 10:52
 */
interface LoginContract {

    interface  Presenter :BasePresenter{

        fun  login(userName:String,password :String)
    }




    interface  View{
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInSuccess()
        fun onLoggedInFailed()
    }
}