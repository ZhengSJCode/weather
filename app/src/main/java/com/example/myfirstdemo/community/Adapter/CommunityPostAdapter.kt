package com.example.myfirstdemo.community.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstdemo.community.Post
import com.example.myfirstdemo.R
import com.squareup.picasso.Picasso // 使用 Picasso 加载图片
import kotlin.math.log

class CommunityPostAdapter(
    private val context: Context,
    private val postList: List<Post>
) : RecyclerView.Adapter<CommunityPostAdapter.PostViewHolder>() {

    private val TAG: String = "CommunityPostAdapter"

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.user_name)
        val content: TextView = view.findViewById(R.id.post_content)
        val postImage: ImageView = view.findViewById(R.id.post_image)
        val timestamp: TextView = view.findViewById(R.id.post_timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        Log.d(TAG, "onCreateViewHolder: ")
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.userName.text = post.userName
        holder.content.text = post.content
        holder.timestamp.text = post.timestamp
        Picasso.get().load(post.imageUrl).into(holder.postImage) // 使用 Picasso 加载图片
        Log.d(TAG, "onBindViewHolder: ${position}")
    }

    override fun getItemCount(): Int {
        return postList.size
    }
} 