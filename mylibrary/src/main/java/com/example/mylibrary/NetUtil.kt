package com.example.mylibrary

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * <pre>
 * @author : zhengshuaijie
 * e-mail : zhengshuaijie@lixiang.com
 * time : 2025/1/20
 * desc :
 * version : 1.0
</pre>
 */
class NetUtil private constructor(){
    private val TAG = "NetUtil"
    companion object{
        val instance:NetUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            NetUtil()
        }
    }
    fun doGet(url: String): String {
        val urlConnection: HttpURLConnection
        val bufferedReader : BufferedReader
        val bookJsonString:String
        try {
            var requestUrl = URL(url)
            urlConnection = requestUrl.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connectTimeout = 8000
            urlConnection.connect()

            val inputStream:InputStream = urlConnection.inputStream

            inputStream.use {
                bufferedReader = BufferedReader(InputStreamReader(it))
                val stringBuilder = StringBuilder()
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                    stringBuilder.append("\n")
                }
                bookJsonString = stringBuilder.toString()
            }

            urlConnection.disconnect()
            return bookJsonString
        }catch (e:Exception) {
            e.printStackTrace()
            return "error"
        }

    }


}
