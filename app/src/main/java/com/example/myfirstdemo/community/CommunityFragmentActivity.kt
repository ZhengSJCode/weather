package com.example.myfirstdemo.community

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstdemo.community.Adapter.CommunityPostAdapter
import com.example.myfirstdemo.databinding.CommunityFragmentActivityBinding
import com.example.mylibrary.WeatherBean
import com.example.mylibrary.WeatherData
import com.example.mylibrary.api.DefaultService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CommunityFragmentActivity : FragmentActivity() {


    private lateinit var mbinding: CommunityFragmentActivityBinding
    private var weatherDataList: List<WeatherData>? = null
    private var service: DefaultService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = CommunityFragmentActivityBinding.inflate(layoutInflater)
        setContentView(mbinding.root)
        initView()
        initDatum()
    }

    private fun initDatum() {
        // 模拟数据
        val postList = listOf(
            Post("用户1", "这是我的第一条帖子！", "https://www.wikihow.com/images/thumb/f/fc/Get-the-URL-for-Pictures-Step-1-Version-6.jpg/v4-728px-Get-the-URL-for-Pictures-Step-1-Version-6.jpg.webp", "刚刚"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户2", "今天天气不错！", "https://example.com/image2.jpg", "1分钟前"),
            Post("用户3", "分享一下我的旅行照片！", "https://example.com/image3.jpg", "2分钟前")
        )
        mbinding.recyclerView.adapter = CommunityPostAdapter(this, postList)
    }

    private fun initView() {
        mbinding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    /**
     * retrofit get请求
     */
    private fun testRetrofitGet() {
        service!!.getWeatherByCity("北京")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<WeatherBean> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(s: WeatherBean) {
                    val sheet: WeatherBean = s
                    Log.d("CommunityFragmentActivity", "onNext: " + sheet)

                    // 将 WeatherData 转换为 Post
                    val postList = sheet.data.mapNotNull { weatherData ->
                        weatherData.let {
                            Post(
                                userName = "用户", // 可以根据需要设置用户名
                                content = "天气情况: ${it.wea}", // 假设 WeatherData 有 wea 属性
                                imageUrl = "https://example.com/image.jpg", // 替换为实际图片链接
                                timestamp = "刚刚" // 可以根据需要设置时间戳
                            )
                        }
                    } 

                    // 更新适配器
                    mbinding.recyclerView.adapter = CommunityPostAdapter(this@CommunityFragmentActivity, postList)
                }

                override fun onError(e: Throwable) {
                    Log.d("CommunityFragmentActivity", "onError: " + e.localizedMessage)
                }

                override fun onComplete() {
                }
            })
    }


}
