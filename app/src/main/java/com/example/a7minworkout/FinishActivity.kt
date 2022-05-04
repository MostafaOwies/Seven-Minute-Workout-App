package com.example.a7minworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.example.a7minworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding : ActivityFinishBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityFinishBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        //support the action bar
        setSupportActionBar(binding?.finishToolbar)
        if (supportActionBar !=null){
            // display the UP/Home button
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.finishToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }

        binding?.finishBTN?.setOnClickListener(){
            finish()
        }
        val historyDao =(application as WorkoutApp).db.historyDao()
        addDateToDB(historyDao)

    }


    private fun addDateToDB(historyDao: HistoryDao){
        val calendar= Calendar.getInstance()
        val dateTime=calendar.time
        val sdf= SimpleDateFormat("dd MMM yyy HH:mm:ss", Locale.getDefault())
        val date=sdf.format(dateTime)

        lifecycleScope.launch{
            historyDao.insert(HistoryEntity(date = date))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}