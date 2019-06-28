package com.example.im.contract

import android.os.Handler
import android.os.Looper

/**
 * @author  Mloong
 * date  2019/6/27 22:58
 */
interface BasePresenter {

    companion object{
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }

    }


    fun   uiThread(f : () -> Unit){
        handler.post { f() }
    }

}