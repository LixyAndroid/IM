package com.example.im.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import cn.bmob.v3.Bmob
import com.example.im.BuildConfig
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


    val messageListener = object  :EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            //如果在前台则播放短的声音

            //如果在后台则播放长的声音


        }
    }


    override fun onCreate() {
        super.onCreate()

        instance = this

        val options = EMOptions()

//    // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(true)
////
////    // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
////            options.setAutoTransferMessageAttachments(true)
////    // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
////            options.setAutoDownloadThumbnail(true)

         //初始化
        EMClient.getInstance().init(applicationContext, options)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        //第一：默认初始化
        Bmob.initialize(this, "2887467008a23530373c4c5ca79772ee")



        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }


    //判断app是否在前台
    private fun isPoreground() :Boolean{
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (runningAppProgress in activityManager.runningAppProcesses ){
            if (runningAppProgress.processName == packageName){
                //找到了app的进程

                return    runningAppProgress.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }

        }
        return  false
    }


}