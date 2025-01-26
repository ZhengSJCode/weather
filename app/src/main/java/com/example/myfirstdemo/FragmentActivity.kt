package com.example.myfirstdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.myfirstdemo.databinding.FragmentActivityBinding

class FragmentActivity : FragmentActivity() {
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var mCities: Array<String>
    private lateinit var mbinding: FragmentActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = FragmentActivityBinding.inflate(layoutInflater)
        setContentView(mbinding.root)
        initView()
    }

    private fun initView() {

        mCities = resources.getStringArray(R.array.cities)
//        val mCities = listOf("Beijing", "Shanghai", "Guangzhou")

        arrayAdapter = ArrayAdapter<String>(this, R.layout.fragment_activity, mCities)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

//        mbinding.spCity.adapter = arrayAdapter

        mbinding.spCity.onItemSelectedListener = object :OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@FragmentActivity,"你好",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}
