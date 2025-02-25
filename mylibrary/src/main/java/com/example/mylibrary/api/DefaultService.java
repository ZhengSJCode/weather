package com.example.mylibrary.api;

import com.example.mylibrary.WeatherBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 默认远程数据源
 */
public interface DefaultService {
    /**
     * 歌单列表
     *
     * @return
     */
    @GET("api?unescape=1&version=v91&appid=71515994&appsecret=iH0Zss5I")
    Observable<WeatherBean> getWeatherByCity(@Query(value = "city") String city);
}
