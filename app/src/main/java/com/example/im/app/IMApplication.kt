package com.example.im.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import cn.bmob.v3.Bmob
import com.example.im.BuildConfig
import com.example.im.R
import com.example.im.adapter.EMMessageListenerAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions


/**
 * @author  Mloong
 * date  2019/6/28 10:40
 *
 *
 *
 *        ┏┓　　　┏┓
 *      ┏┛┻━━━┛┻┓
 *      ┃　　　　　　　┃
 *      ┃　　　━　　　┃
 *      ┃　┳┛　┗┳　┃
 *      ┃　　　　　　　┃
 *      ┃　　　┻　　　┃
 *      ┃　　　　　　　┃
 *      ┗━┓　　　┏━┛
 *         ┃　　　┃   神兽保佑
 *         ┃　　　┃   代码无BUG！
 *         ┃　　　┗━━━┓
 *         ┃　　　　　　　┣┓
 *         ┃　　　　　　　┏┛
 *         ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *
 *
 */

class IMApplication :Application() {


    companion object{
        lateinit var  instance:IMApplication
    }





    private val messageListener = object :EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {

              //如果在前台则播放短的声音
            if (isForeground()){
                val player  =    MediaPlayer.create(instance,R.raw.duan)
                player.start()
            }
            else{
              //如果在后台台则播放长的声音
                val player  =    MediaPlayer.create(instance,R.raw.yulu)
                player.start()

            }


        }
    }


    override fun onCreate() {
        super.onCreate()

        instance = this

        val options = EMOptions()

         //初始化
        EMClient.getInstance().init(applicationContext, options)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        //第一：默认初始化
        Bmob.initialize(this, "2887467008a23530373c4c5ca79772ee")

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }


    //判断app是否在前台
    private fun isForeground() :Boolean{
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (runningAppProgress in activityManager.runningAppProcesses ){
            if (runningAppProgress.processName == packageName){
                //找到了app的进程
                return  runningAppProgress.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }

        }
        return  false
    }


}