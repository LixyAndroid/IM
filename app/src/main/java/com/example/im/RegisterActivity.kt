package com.example.im

import com.example.im.contract.RegisterContract
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


/**
 * @author  Mloong
 * date  2019/6/28 15:30
 */
class RegisterActivity :BaseActivity(),RegisterContract.View {


    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStratRegister() {
       showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dismissProgress()

        finish()
    }

    override fun onRegisterFailde() {
        dismissProgress()
        toast(R.string.register_failed)
    }

    override fun getLayoutReId(): Int  = R.layout.activity_register

}