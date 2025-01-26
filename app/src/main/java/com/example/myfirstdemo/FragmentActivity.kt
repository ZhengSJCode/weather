package com.example.myfirstdemo

import android.os.Bundle
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.myfirstdemo.databinding.FragmentActivityBinding

class FragmentActivity : FragmentActivity() {
    private lateinit var mbinding: FragmentActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = FragmentActivityBinding.inflate(layoutInflater)
        setContentView(mbinding.root)
        initView()
    }

    private fun initView() {

        val mCities = resources.getStringArray(R.array.cities)

//        val arrayAdapter = ArrayAdapter(this, R.id.sp_city, mCities)
//        mbinding.spCity.adapter = arrayAdapter

//        mbinding.spCity.onItemSelectedListener = OnItemSelectedListener()
    }
}
