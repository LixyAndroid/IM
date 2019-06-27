package com.example.im

import android.os.Handler
import com.example.im.contract.SplashContract
import org.jetbrains.anko.startActivity

/**
 * @author  Mloong
 * date  2019/6/27 21:47
 */
class SplashActivity : BaseActivity() ,SplashContract.View{

    companion object{
        val DELAY = 2000L
    }

    val handler by lazy {
        Handler()
    }


    //延时2s,跳转到登录界面
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, DELAY)


    }


    //跳转到主界面
    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()

    }

    override fun getLayoutReId(): Int =
        R.layout.activity_splash


}