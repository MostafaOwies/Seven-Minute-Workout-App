package com.example.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryActivity : AppCompatActivity() {
    private var binding : ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.historyToolBar)
        if (supportActionBar !=null){
            // display the UP/Home button
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="History"
        }
        binding?.historyToolBar?.setNavigationOnClickListener{
            // customDialogBackButton()
            onBackPressed()
        }

        val historyDao =(application as WorkoutApp).db.historyDao()

        lifecycleScope.launch{
            historyDao.getAll().collect{
                val list= java.util.ArrayList(it)
                addDateToRecyclerView(list,historyDao)
            }
        }
    }


    private fun addDateToRecyclerView(historyList:ArrayList<HistoryEntity>,historyDao: HistoryDao){

        if(historyList.isNotEmpty()){
            val adapter=HistoryAdapter(historyList)
            binding?.historyRV?.layoutManager=LinearLayoutManager(this)
            binding?.historyRV?.adapter=adapter

        }

    }


}