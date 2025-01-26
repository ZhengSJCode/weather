package com.example.mylibrary

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun main() {

        val result = NetUtil.instance.doGet("http://v1.yiketianqi.com/api?unescape=1&version=v91&appid=71515994&appsecret=iH0Zss5I")
        println(result)
    }
    @Test
    fun main1() {
        val weatherBean:WeatherBean = WeatherUtil.instance.getWeather()

        println(weatherBean.aqi)

    }
}