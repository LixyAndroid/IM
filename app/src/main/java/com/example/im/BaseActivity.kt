package com.example.im

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author  Mloong
 * date  2019/6/27 21:33
 */
abstract class BaseActivity :AppCompatActivity(){

    val  progressDialog by lazy {
        ProgressDialog(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutReId())

        init()
    }

    open fun init() {

        //初始化一些公共的功能，比如摇一摇，子类也可以复写该方法，完成自己的初始化
    }


    //子类必须实现该方法返回一个布局资源的id
    abstract fun getLayoutReId(): Int



    fun showProgress(message:String){
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dismissProgress(){

        progressDialog.dismiss()
    }
}