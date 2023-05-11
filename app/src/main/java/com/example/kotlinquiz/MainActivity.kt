package com.example.kotlinquiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnPlay.setOnClickListener {
            val inent = Intent(this, InGame::class.java)
            startActivity(inent)
        }


        binding.btnQuestions.setOnClickListener {
            val inent = Intent(this, editQuestions::class.java)
            startActivity(inent)
        }


    }
}