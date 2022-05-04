package com.example.a7minworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minworkout.databinding.HistoryRowBinding

class HistoryAdapter(private val historyList: List<HistoryEntity>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding:HistoryRowBinding):RecyclerView.ViewHolder(binding.root){
        val  historyTV=binding.historyDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HistoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =historyList[position]
        holder.historyTV.text=item.date
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}