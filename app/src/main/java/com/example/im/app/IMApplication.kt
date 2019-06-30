package com.example.im.app

import android.app.*
import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import cn.bmob.v3.Bmob
import com.example.im.BuildConfig
import com.example.im.R
import com.example.im.adapter.EMMessageListenerAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody


import androidx.core.app.NotificationCompat
import android.content.Intent
import android.os.Build
import androidx.core.app.TaskStackBuilder
import com.example.im.ui.activity.ChatActivity


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


    lateinit var player :MediaPlayer

    companion object{
        lateinit var  instance:IMApplication
    }



    private val messageListener = object :EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {

              //如果在前台则播放短的声音
            if (isForeground()){
                 player = MediaPlayer.create(instance,R.raw.duan)
                player.stop()
                player.prepare()

                player.start()


            }
            else{
              //如果在后台台则播放长的声音
                player = MediaPlayer.create(instance,R.raw.yulu)

                player.stop()
                player.prepare()
                player.start()

                showNotification(p0)

            }



        }




    }



    private fun showNotification(p0: MutableList<EMMessage>?) {


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        p0?.forEach {
            var contentText = getString(R.string.no_text_message)
            if (it.type == EMMessage.Type.TXT){
                contentText = (it.body as EMTextMessageBody).message
            }


            val intent = Intent(this,ChatActivity::class.java)
            intent.putExtra("username",it.conversationId())
         //   val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)


            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)

            val pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= 26) {
                //当sdk版本大于26
                val id = "channel_1"
                val description = "143"
                val importance = NotificationManager.IMPORTANCE_LOW
                val channel = NotificationChannel(id, description, importance)
                //                     channel.enableLights(true);
                //                     channel.enableVibration(true);//
                notificationManager.createNotificationChannel(channel)
                val notification = Notification.Builder(instance, id)
                    .setContentTitle(getString(R.string.receive_new_message))
                    .setContentText(contentText)
                    .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.avatar1))
                    .setSmallIcon(R.mipmap.ic_contact)
                    .setContentIntent(pendingIntent) //点击跳转到聊天页面
                    .setAutoCancel(true) //点击自动消失
                    .build()
                notificationManager.notify(1, notification)
            } else {
                //当sdk版本小于26
                val notification = NotificationCompat.Builder(instance)
                    .setContentTitle(getString(R.string.receive_new_message))
                    .setContentText(contentText)
                    .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.avatar1))
                    .setSmallIcon(R.mipmap.ic_contact)
                    .setContentIntent(pendingIntent) //点击跳转到聊天页面
                    .setAutoCancel(true) //点击自动消失
                    .build()
                notificationManager.notify(1, notification)
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