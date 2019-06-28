package com.example.im.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @author  Mloong
 * date  2019/6/27 21:37
 */
abstract class BaseFragment:Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
       =   inflater.inflate(getLayoutResId(),null)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        init()


    open fun init(){
        //初始化一些公共的功能，比如摇一摇，子类也可以复写该方法，完成自己的初始化
    }

    abstract fun getLayoutResId(): Int


}