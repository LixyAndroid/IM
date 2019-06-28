package com.example.im.ui.activity

import com.example.im.R
import com.example.im.contract.RegisterContract
import com.example.im.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


/**
 * @author  Mloong
 * date  2019/6/28 15:30
 */
class RegisterActivity : BaseActivity(),RegisterContract.View {




    val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()

        register.setOnClickListener {
            register()
        }

        confirmPassword.setOnEditorActionListener { p0, p1, p2 ->
            register()
            true
        }


    }

    fun  register(){
        //隐藏软键盘

        hideSoftKeyboard()

        val  userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        val confirmPasswordString =confirmPassword.text.trim().toString()

        presenter.register(userNameString,passwordString,confirmPasswordString)
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
       showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dismissProgress()
        toast(R.string.register_success)
        finish()
    }

    override fun onRegisterFailed() {
        dismissProgress()
        toast(R.string.register_failed)
    }

    override fun onUserExist() {

        dismissProgress()
        toast(R.string.user_already_exist)
    }

    override fun getLayoutReId(): Int  = R.layout.activity_register

}