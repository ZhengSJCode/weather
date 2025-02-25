package com.example.myfirstdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstdemo.Adapter.FeatureWeatherAdapter
import com.example.myfirstdemo.databinding.FragmentActivityBinding
import com.example.mylibrary.WeatherBean
import com.example.mylibrary.WeatherData
import com.example.mylibrary.WeatherUtil
import com.example.mylibrary.api.DefaultService
import com.example.mylibrary.api.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class CommunityFragmentActivity : FragmentActivity() {


    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var mCities: Array<String>
    private lateinit var mbinding: FragmentActivityBinding
    private var weatherDataList: List<WeatherData>? = null
    private var service: DefaultService? = null
    private var mHandler: Handler = Handler(Looper.getMainLooper()){
        when(it.what){
            1 -> {
                setWeather(it.obj)
                mbinding.recyclerView.adapter = FeatureWeatherAdapter(this,weatherDataList)
            };
            else -> {Toast.makeText(this,"error", LENGTH_SHORT).show()}
        }
        return@Handler true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = FragmentActivityBinding.inflate(layoutInflater)
        setContentView(mbinding.root)
        initView()
    }

    private fun initData(string: String="北京") {
        Thread{
            val weatherBean = WeatherUtil.instance.getWeather(string)
            val msg =  Message()
            msg.what = 1
            msg.obj = weatherBean
            mHandler.sendMessage(msg)
        }.start()

        val okHttpClient: OkHttpClient = NetworkModule.provideOkHttpClient()
        val retrofit: Retrofit = NetworkModule.provideRetrofit(okHttpClient)
        service = retrofit.create(DefaultService::class.java)

    }

    private fun initView() {
        // Step 1: 准备数据源（可以是 List 或 Array）
        mCities = resources.getStringArray(R.array.cities)

        // Step 2: 创建 ArrayAdapter 并绑定数据源
        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mCities)

        // 设置下拉样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Step 3: 将 ArrayAdapter 设置给 Spinner
        val spinner = findViewById<AppCompatSpinner>(R.id.sp_city)
        spinner.adapter = arrayAdapter

        mbinding.spCity.adapter = arrayAdapter

        mbinding.spCity.onItemSelectedListener = object :OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                initData(p0?.selectedItem.toString())
                Toast.makeText(this@CommunityFragmentActivity,"你好"+p0?.selectedItem, LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        mbinding.recyclerView.adapter = FeatureWeatherAdapter(this,weatherDataList)
        mbinding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


    }
    @SuppressLint("SetTextI18n")
    private fun setWeather(obj: Any?) {
        val sheshidu =  "℃"
        val weatherBean = obj as WeatherBean
        println(weatherBean)
        var weatherData: WeatherData = weatherBean.data.get(0)
        val alarm = weatherData.alarm?.get(0)
        mbinding.item1.text = weatherData.tem + sheshidu
        mbinding.item2.text = weatherData.tem1 +sheshidu +"~" + weatherData.tem2 +sheshidu
        mbinding.item3.text = weatherData.wind[0] + weatherData.windSpeed
        mbinding.item4.text = weatherData.air +sheshidu +" " + weatherData.airLevel
        mbinding.item5.text = alarm?.alarmContent

        weatherDataList = weatherBean.data
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
                    Log.d(
                        "CommunityFragmentActivity",
                        "onNext: " + sheet
                    )
                }

                override fun onError(e: Throwable) {
                    Log.d(
                        "CommunityFragmentActivity",
                        "onError: " + e.localizedMessage
                    )
                }

                override fun onComplete() {
                }
            })
    }


}
