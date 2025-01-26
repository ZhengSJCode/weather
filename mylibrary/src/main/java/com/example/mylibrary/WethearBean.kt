package com.example.mylibrary

import com.google.gson.annotations.SerializedName

/**
 * <pre>
 * @author : zhengshuaijie
 * e-mail : zhengshuaijie@lixiang.com
 * time : 2025/1/20
 * desc :
 * version : 1.0
</pre> *
 */
data class WeatherBean(

    @SerializedName("cityid") val cityId: String,
    @SerializedName("city") val city: String,
    @SerializedName("cityEn") val cityEn: String,
    @SerializedName("country") val country: String,
    @SerializedName("countryEn") val countryEn: String,
    @SerializedName("update_time") val updateTime: String,
    @SerializedName("data") val data: List<WeatherData>,
    @SerializedName("aqi") val aqi: Aqi
)

data class WeatherData(
    @SerializedName("day") val day: String,
    @SerializedName("date") val date: String,
    @SerializedName("week") val week: String,
    @SerializedName("wea") val wea: String,
    @SerializedName("wea_img") val weaImg: String,
    @SerializedName("wea_day") val weaDay: String,
    @SerializedName("wea_day_img") val weaDayImg: String,
    @SerializedName("wea_night") val weaNight: String,
    @SerializedName("wea_night_img") val weaNightImg: String,
    @SerializedName("tem") val tem: String,
    @SerializedName("tem1") val tem1: String,
    @SerializedName("tem2") val tem2: String,
    @SerializedName("humidity") val humidity: String,
    @SerializedName("visibility") val visibility: String,
    @SerializedName("pressure") val pressure: String,
    @SerializedName("win") val wind: List<String>,
    @SerializedName("win_speed") val windSpeed: String,
    @SerializedName("win_meter") val windMeter: String,
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("air") val air: String,
    @SerializedName("air_level") val airLevel: String,
    @SerializedName("air_tips") val airTips: String?,
    @SerializedName("phrase") val phrase: String,
    @SerializedName("narrative") val narrative: String,
    @SerializedName("moonrise") val moonrise: String,
    @SerializedName("moonset") val moonset: String,
    @SerializedName("moonPhrase") val moonPhrase: String,
    @SerializedName("rain") val rain: String,
    @SerializedName("uvIndex") val uvIndex: String,
    @SerializedName("uvDescription") val uvDescription: String,
    @SerializedName("rain_pcpn") val rainPcpn: String,
    @SerializedName("alarm") val alarm: List<Alarm>?
)

data class Alarm(
    @SerializedName("alarm_type") val alarmType: String,
    @SerializedName("alarm_level") val alarmLevel: String,
    @SerializedName("alarm_title") val alarmTitle: String,
    @SerializedName("alarm_content") val alarmContent: String
)

data class Aqi(
    @SerializedName("update_time") val updateTime: String,
    @SerializedName("cityid") val cityId: String,
    @SerializedName("city") val city: String,
    @SerializedName("air") val air: String,
    @SerializedName("air_level") val airLevel: String,
    @SerializedName("air_tips") val airTips: String,
    @SerializedName("pm25") val pm25: String,
    @SerializedName("pm25_desc") val pm25Desc: String,
    @SerializedName("pm10") val pm10: String,
    @SerializedName("pm10_desc") val pm10Desc: String,
    @SerializedName("o3") val o3: String,
    @SerializedName("o3_desc") val o3Desc: String,
    @SerializedName("no2") val no2: String,
    @SerializedName("no2_desc") val no2Desc: String,
    @SerializedName("so2") val so2: String,
    @SerializedName("so2_desc") val so2Desc: String,
    @SerializedName("co") val co: String,
    @SerializedName("co_desc") val coDesc: String,
    @SerializedName("kouzhao") val kouzhao: String,
    @SerializedName("yundong") val yundong: String,
    @SerializedName("waichu") val waichu: String,
    @SerializedName("kaichuang") val kaichuang: String,
    @SerializedName("jinghuaqi") val jinghuaqi: String
)
