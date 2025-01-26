package com.example.mylibrary

import com.google.gson.Gson

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
        val jsonObject =  gson.fromJson(result, WeatherBean::class.java)
        return jsonObject
    }
}