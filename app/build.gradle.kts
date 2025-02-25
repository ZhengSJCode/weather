import org.jetbrains.kotlin.types.checker.TypeRefinementSupport.EnabledUninitialized.isEnabled

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myfirstdemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myfirstdemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(project(":mylibrary"))
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
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

    implementation ("com.squareup.picasso:picasso:2.71828")

}