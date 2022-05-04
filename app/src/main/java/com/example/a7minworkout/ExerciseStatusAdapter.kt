package com.example.a7minworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minworkout.databinding.ActivityMainBinding
import com.example.a7minworkout.databinding.ExerciseStatusLayoutBinding

class ExerciseStatusAdapter(val exerciseListItem :List<ExerciseModel>) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    //for binding a seperate XML file
     inner class ViewHolder (binding: ExerciseStatusLayoutBinding)
         :RecyclerView.ViewHolder(binding.root){
            val exerciseItem = binding.itemText
         }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                                    //when inflating outside of an activity we need to pass the layout inflater from a given context
        return ViewHolder(ExerciseStatusLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model : ExerciseModel=exerciseListItem[position]
        holder.exerciseItem.text=model.getId().toString()

        when{
            model.getIsSelected()->{
                holder.exerciseItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.item_thin_color_accent_border)
                holder.exerciseItem.setTextColor(Color.parseColor("#3A3B3C"))
            }
            model.getIsCompleted()->{
                holder.exerciseItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.item_circular_color_accent_background)
                holder.exerciseItem.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
            else->{
                holder.exerciseItem.background=
                    ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.item_circular_color_grey_background)
                holder.exerciseItem.setTextColor(Color.parseColor("#3A3B3C"))
            }
        }
    }

    override fun getItemCount(): Int {
       return exerciseListItem.size
    }

}