package com.example.myfirstdemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.myfirstdemo.databinding.ActivityMainBinding
import com.example.mylibrary.NetUtil
import com.example.mylibrary.WeatherBean
import com.example.mylibrary.WeatherUtil

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
        mbinding.btnSF.text = "跳转到静态fragment"
        mbinding.btnSF.setOnClickListener {
            Intent(this, FragmentActivity::class.java).apply {
                startActivity(this)
            }
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

