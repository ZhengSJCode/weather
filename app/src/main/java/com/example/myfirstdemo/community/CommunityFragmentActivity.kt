package com.example.myfirstdemo.community

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstdemo.community.Adapter.CommunityPostAdapter
import com.example.myfirstdemo.databinding.CommunityFragmentActivityBinding
import com.example.mylibrary.WeatherBean
import com.example.mylibrary.WeatherData
import com.example.mylibrary.api.DefaultService
import com.example.mylibrary.api.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class CommunityFragmentActivity : FragmentActivity() {

    private lateinit var mbinding: CommunityFragmentActivityBinding
    private var postList: MutableList<Post> = mutableListOf() // 使用 MutableList
    private var service: DefaultService? = null
    private var isLoading = false // 添加一个标志来防止重复加载

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = CommunityFragmentActivityBinding.inflate(layoutInflater)
        setContentView(mbinding.root)

        // 初始化 service
        val okHttpClient: OkHttpClient = NetworkModule.provideOkHttpClient()
        val retrofit: Retrofit = NetworkModule.provideRetrofit(okHttpClient)
        service = retrofit.create(DefaultService::class.java)

        initView()
        initDatum()
    }

    private fun initDatum() {
        // 模拟数据
        postList.addAll(listOf(
            Post("用户1", "这是我的第一条帖子！", "https://www.wikihow.com/images/thumb/f/fc/Get-the-URL-for-Pictures-Step-1-Version-6.jpg/v4-728px-Get-the-URL-for-Pictures-Step-1-Version-6.jpg.webp", "刚刚"),
            Post("用户2", "这是我的第一条帖子！", "https://www.wikihow.com/images/thumb/f/fc/Get-the-URL-for-Pictures-Step-1-Version-6.jpg/v4-728px-Get-the-URL-for-Pictures-Step-1-Version-6.jpg.webp", "刚刚"),
            Post("用户3", "这是我的第一条帖子！", "https://www.wikihow.com/images/thumb/f/fc/Get-the-URL-for-Pictures-Step-1-Version-6.jpg/v4-728px-Get-the-URL-for-Pictures-Step-1-Version-6.jpg.webp", "刚刚")
        ))
        mbinding.recyclerView.adapter = CommunityPostAdapter(this, postList)
    }

    private fun initView() {
        mbinding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        // 添加滚动监听器
        mbinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 检查是否滚动到底部
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount - 1) {
                    // 到达底部，调用获取新数据的方法
                    testRetrofitGet()
                }
            }
        })
    }

    /**
     * retrofit get请求
     */
    private fun testRetrofitGet() {
        if (isLoading) return // 如果正在加载，则返回
        isLoading = true // 设置为正在加载

        service!!.getWeatherByCity("北京")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<WeatherBean> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(s: WeatherBean) {
                    val sheet: WeatherBean = s
                    Log.d("CommunityFragmentActivity", "onNext: " + sheet)

                    // 将 WeatherData 转换为 Post 并扩充 postList
                    val newPosts = sheet.data.mapNotNull { weatherData ->
                        weatherData.let {
                            Post(
                                userName = "用户", // 可以根据需要设置用户名
                                content = "天气情况: ${it.wea}", // 假设 WeatherData 有 wea 属性
                                imageUrl = "https://example.com/image.jpg", // 替换为实际图片链接
                                timestamp = "刚刚" // 可以根据需要设置时间戳
                            )
                        }
                    }

                    // 使用适配器的 updatePosts 方法更新数据
                    (mbinding.recyclerView.adapter as CommunityPostAdapter).updatePosts(newPosts)
                    isLoading = false // 数据加载完成，重置标志
                }

                override fun onError(e: Throwable) {
                    Log.d("CommunityFragmentActivity", "onError: " + e.localizedMessage)
                    isLoading = false // 发生错误，重置标志
                }

                override fun onComplete() {
                }
            })
    }
}
