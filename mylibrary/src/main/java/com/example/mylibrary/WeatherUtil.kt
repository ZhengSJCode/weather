package com.example.mylibrary

import android.util.Log
import com.google.gson.Gson

import java.io.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
/**
 * <pre>
 * @author : zhengshuaijie
 * e-mail : zhengshuaijie@lixiang.com
 * time : 2025/1/20
 * desc :
 * version : 1.0
</pre>
 */
class WeatherUtil {

    companion object{
        val instance:WeatherUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            WeatherUtil()
        }
    }

    fun getWeather(city:String = "北京") : WeatherBean {
        val gson = Gson()
        val result = NetUtil.instance.doGet("https://v1.yiketianqi.com/api?unescape=1&version=v91&appid=71515994&appsecret=iH0Zss5I&city=$city")
        val result1 = testGet()
        val jsonObject =  gson.fromJson(result, WeatherBean::class.java)
        return jsonObject
    }
    private fun testGet(city:String = "Beijing") {
        val client: OkHttpClient = OkHttpClient()

        var url: String = Config.ENDPOINT + "api?unescape=1&version=v91&appid=71515994&appsecret=iH0Zss5I&city=$city"
        url = "https://wwww.baidu.com"
        Log.v(
            "WeatherUtil",
            "url: " + url
        )
        val request: Request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(
                    "WeatherUtil",
                    "get error: " + e
                )
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log.d(
                    "WeatherUtil",
                    "get success: " + response
                )
            }
        })
    }

}
fun main(){
    WeatherUtil.instance.getWeather()
}