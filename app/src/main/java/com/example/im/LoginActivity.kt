package com.example.im

import android.view.KeyEvent
import android.widget.TextView
import com.example.im.contract.LoginContract
import com.example.im.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.w3c.dom.Text

/**
 * @author  Mloong
 * date  2019/6/27 23:05
 */
class LoginActivity: BaseActivity() ,LoginContract.View{

    val  presenter = LoginPresenter(this)

    override fun init() {
        super.init()
        login.setOnClickListener { login() }

//        password.setOnEditorActionListener(object : TextView.OnEditorActionListener{
//            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
//                login()
//                return true
//            }
//        })

        password.setOnEditorActionListener { p0, p1, p2 ->
            login()
            true
        }

    }

    fun login(){
        //隐藏软键盘

        hideSoftKeyboard()
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()

        presenter.login(userNameString,passwordString)
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)

    }

    override fun onStartLogin() {
        //弹出一个进度条
        showProgress(getString(R.string.logging))
    }

    override fun onLoggedInSuccess() {
        //隐藏进度条
        dismissProgress()

        //进入主界面
        startActivity<MainActivity>()

        //退出LoginActivity
        finish()


    }

    override fun onLoggedInFailed() {

        //隐藏进度条
        dismissProgress()
        //弹出toast
        toast(R.string.login_failed)

    }

    override fun getLayoutReId(): Int = R.layout.activity_login



}