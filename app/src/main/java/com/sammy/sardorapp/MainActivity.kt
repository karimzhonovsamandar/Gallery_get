package com.sammy.sardorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sammy.sardorapp.adapter.ImageAdapter
import com.sammy.sardorapp.databinding.ActivityMainBinding
import com.sammy.sardorapp.db.MyDbHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var imageAdapter: ImageAdapter
    lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)

        imageAdapter = ImageAdapter(myDbHelper.getAllDate())
        binding.rv.adapter = imageAdapter // Привязываем адаптер к RecyclerView
        imageAdapter.notifyDataSetChanged()
        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
            finish()
        }

    }

    override fun onResume() {
        super.onResume()


    }
}