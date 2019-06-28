package com.example.im.presenter
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.im.contract.RegisterContract
import com.example.im.extentions.isValidPassword
import com.example.im.extentions.isValidUserName
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


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
                    //开始注册
                    registerBmob(userName,password)

                }else view.onConfirmPasswordError()

            }else view.onPasswordError()

        }else view.onUserNameError()

    }

    /**
     * 账号密码登录
     */
    private fun registerBmob(userName: String,password: String) {
        val bu = BmobUser()
        //此处替换为你的用户名
        bu.username = userName
        //此处替换为你的密码
        bu.setPassword(password)
        bu.signUp<BmobUser>(object : SaveListener<BmobUser>() {
            override fun done(s: BmobUser, e: BmobException?) {
                if (e == null) {
                    //注册成功
                    //注册到环信
                    registerEaseMob(userName,password)

                } else {
                   //注册失败
                    view.onRegisterFailde()
                }

            }
        })
    }

    private fun registerEaseMob(userName: String, password: String) {


        //线程切换
        doAsync {
                try {

                    //注册失败会抛出HyphenateException
                    EMClient.getInstance().createAccount(userName, password) //同步方法

                    //注册成功
                    uiThread { view.onRegisterSuccess() }
                }catch(e:HyphenateException){

                    //注册失败
                    uiThread { view.onRegisterFailde() }
                }



        }

    }

}