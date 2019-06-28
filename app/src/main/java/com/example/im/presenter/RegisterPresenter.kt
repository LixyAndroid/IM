package com.example.im.presenter

import com.example.im.contract.RegisterContract
import com.example.im.extentions.isValidPassword
import com.example.im.extentions.isValidUserName

/**
 * @author  Mloong
 * date  2019/6/28 15:47
 */
class RegisterPresenter(val view: RegisterContract.View):RegisterContract.Presenter {

    override fun register(userName: String, password: String, confirmPassword: String) {

        if (userName.isValidUserName()){
            //检查密码

            if (password.isValidPassword()){

                //检查确认密码
                if (password.equals(confirmPassword)){
                    //密码和确认密码一致
                    view.onStratRegister()

                }else view.onConfirmPasswordError()

            }else view.onPasswordError()

        }else view.onUserNameError()

    }

}