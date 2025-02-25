package com.example.myfirstdemo.community

data class Post(
    val userName: String,
    val content: String,
    val imageUrl: String, // 帖子中的图片
    val timestamp: String // 帖子发布时间
) 