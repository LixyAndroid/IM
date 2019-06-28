package com.example.im.contract

/**
 * @author  Mloong
 * date  2019/6/28 15:36
 */
interface RegisterContract {

    interface  Presenter : BasePresenter{
        fun register(userName:String,password:String,confirmPassword:String)
    }

    interface  View{
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPasswordError()

        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
        fun onUserExist()

    }
}