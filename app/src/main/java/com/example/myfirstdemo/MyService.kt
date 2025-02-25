package com.example.myfirstdemo

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Message
import android.util.Log
import com.example.mylibrary.WeatherBean
import com.example.mylibrary.WeatherUtil
import kotlin.random.Random

class MyService : Service() {

    private val TAG: String = "MyService"
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        println("执行了onCreat()")

//        //添加下列代码将后台Service变成前台Service
//        //构建"点击通知后打开MainActivity"的Intent对象
//        val notificationIntent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
//
//        //新建Builder对象
//        val builder = Notification.Builder(this).apply {
//            setContentTitle("前台服务通知的标题")    //设置通知的标题
//            setContentText("前台服务通知的内容")     //设置通知的内容
//            setSmallIcon(R.mipmap.ic_launcher)    //设置通知的图标
//            setContentIntent(pendingIntent)        //设置点击通知后的操作
//        }
//
//        val notification = builder.build()  //将Builder对象转变成普通的notification
//        startForeground(1, notification)    //让Service变成前台Service,并在系统的状态栏显示出来
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        Thread {
            val result: WeatherBean = WeatherUtil.instance.getWeather()
            println(result)
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    // 服务中的方法示例
    fun getRandomNumber(): Int {
        return Random.nextInt(100)
    }
}