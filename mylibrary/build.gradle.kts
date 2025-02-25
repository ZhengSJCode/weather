plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.mylibrary"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //region 请求网络相关
    //提示：region这种语法是最新的，推荐使用这种，也更容易阅读，不建议在同一个文件同时使用
    //因为可能会显示出错
    //okhttp
    //https://github.com/square/okhttp
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    //用来打印okhttp请求日志
    //当然也可以自定义
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //retrofit
    //https://github.com/square/retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //使用gson解析json
    //https://github.com/google/gson
    implementation("com.google.code.gson:gson:2.9.0")


    //适配retrofit使用gson解析
    //版本要和retrofit一样
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    //适配retrofit支持rxjava
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")


    //使用了Android响应式编程
    //RxJava和RxAndroid区别？
    //简单来说：就是RxAndroid在RxJava的基础上
    //优化了一些功能
    //增强了Android特有的功能
    //https://github.com/ReactiveX/RxAndroid
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")

    //endregion

}