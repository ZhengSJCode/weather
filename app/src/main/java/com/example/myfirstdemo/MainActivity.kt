package com.example.myfirstdemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.myfirstdemo.databinding.ActivityMainBinding
import com.example.mylibrary.NetUtil
import com.example.mylibrary.WeatherBean
import com.example.mylibrary.WeatherUtil
import android.content.pm.PackageManager

class MainActivity : ComponentActivity() {

    private lateinit var mbinding: ActivityMainBinding
    private var mHandler:Handler = Handler(Looper.getMainLooper()){
        when(it.what){
            1 -> {
                mbinding.tv.text = it.obj.toString()
                Toast.makeText(this,"主线程收到消息",Toast.LENGTH_SHORT).show()
            }
            2->{
//                val weather = it.obj as WeatherBean
                mbinding.tv.text = it.obj.toString()
                Toast.makeText(this,"主线程收到消息",Toast.LENGTH_SHORT).show()
            }
        }
        true
    }
    private var myService: MyService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MyService.LocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 改成ViewBindings
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mbinding.root)

        mbinding.btnB.text = "百度"
        mbinding.btnB.setOnClickListener {
            start()
        }
        mbinding.btnW.text = "天气"
        mbinding.btnW.setOnClickListener {
            startWeather()
        }
        mbinding.btnServiceStart.text = "开启服务"
        mbinding.btnServiceStart.setOnClickListener{
            startService(Intent(this,MyService::class.java))
        }
        mbinding.btnServiceStop.text = "暂停服务"
        mbinding.btnServiceStop.setOnClickListener{
            stopService(Intent(this,MyService::class.java))
        }
        mbinding.btnServiceBind.text = "bind服务"
        mbinding.btnServiceBind.setOnClickListener{
            bindService(Intent(this,MyService::class.java),connection,Context.BIND_AUTO_CREATE)
        }
        mbinding.btnServiceUnBind.text = "unbind服务"
        mbinding.btnServiceUnBind.setOnClickListener{
            unbindService(connection)
        }

        mbinding.btnSF.text = "跳转到静态fragment"
        mbinding.btnSF.setOnClickListener {
            Intent(this, FragmentActivity::class.java).apply {
                startActivity(this)
            }
        }
        mbinding.btnSF.text = "跳转到社区"
        mbinding.btnSF.setOnClickListener {
            Intent(this, CommunityFragmentActivity::class.java).apply {
                startActivity(this)
            }
        }

        // 绑定服务
        Intent(this, MyService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 解绑服务
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    private fun start() {
        Thread {
            val result = getStringFromNet()
            val message: Message = Message()
            message.what = 1
            message.obj = result
            mHandler.sendMessage(message)
        }.start()
        Toast.makeText(this,"已点击",Toast.LENGTH_SHORT).show()
    }
    private fun startWeather() {
        Thread {
            val result:WeatherBean = WeatherUtil.instance.getWeather()
            val message: Message = Message()
            message.what = 2
            message.obj = result
            mHandler.sendMessage(message)
        }.start()
        Toast.makeText(this,"已点击",Toast.LENGTH_SHORT).show()
    }

    private fun getStringFromNet(): String {
        return  NetUtil.instance.doGet("https://www.baidu.com")
    }
}

