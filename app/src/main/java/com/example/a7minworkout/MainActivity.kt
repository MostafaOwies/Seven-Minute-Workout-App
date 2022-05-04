package com.example.a7minworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minworkout.databinding.ActivityBmiactivityBinding
import com.example.a7minworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)//bind the root of the main view
                                    // which is the main constraint layout
                                    // which also contain other views

        binding?.myFrameStart?.setOnClickListener(){
            val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding?.BMI?.setOnClickListener{
            val intent = Intent(this,BMIActivity::class.java)
            startActivity(intent)
        }
        binding?.History?.setOnClickListener{
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }
    }




    //unassign the binding to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}