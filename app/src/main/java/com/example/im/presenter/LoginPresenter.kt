package com.example.im.presenter

import android.view.View
import com.example.im.contract.LoginContract
import com.example.im.extentions.isValidPassword
import com.example.im.extentions.isValidUserName

/**
 * @author  Mloong
 * date  2019/6/28 11:08
 */
class LoginPresenter(val  view: LoginContract.View):LoginContract.Presenter {

    override fun login(userName: String, password: String) {
        if (userName.isValidUserName()){
            //用户名合法，继续校验密码
            if (password.isValidPassword()){
                //密码合法，开始登录
                view.onStartLogin()

                loginEaseMob(userName,password)  //登录到环信

            }else{
                view.onPasswordError()
            }

        }else{
            view.onUserNameError()
        }

    }

    private fun loginEaseMob(userName: String, password: String) {

    }


}