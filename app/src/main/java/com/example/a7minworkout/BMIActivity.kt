package com.example.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    var binding :ActivityBmiactivityBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.bmiToolbar)
        if (supportActionBar !=null){
            // display the UP/Home button
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="BMI Calculator"
        }
        binding?.bmiToolbar?.setNavigationOnClickListener{
           // customDialogBackButton()
            onBackPressed()
        }
        binding?.bmiBTN?.setOnClickListener {
            if (validateUserInput()){
                val heightValue : Double = binding?.height?.text.toString().toDouble()/100
                val weightValue : Double = binding?.weight?.text.toString().toDouble()
                val bmi = weightValue/(heightValue*heightValue)
                displayBMIResults(bmi)
                }
            else{
                Toast.makeText(this@BMIActivity,"Enter a valid value!",
                Toast.LENGTH_SHORT
                    ).show()
            }
        }

        binding?.metricRadio?.setOnClickListener{
            metricSystemVisibility()
        }
        binding?.usRadio?.setOnClickListener {
            usSystemVisibility()
        }
    }

    private fun metricSystemVisibility (){
        binding?.footInchLL?.visibility=View.INVISIBLE
        binding?.poundlayout?.visibility=View.INVISIBLE
        binding?.heightLayout?.visibility=View.VISIBLE
        binding?.weightLayout?.visibility=View.VISIBLE

        binding?.weight?.text?.clear()
        binding?.height?.text?.clear()
    }

    private fun usSystemVisibility(){
        binding?.footInchLL?.visibility=View.VISIBLE
        binding?.poundlayout?.visibility=View.VISIBLE
        binding?.heightLayout?.visibility=View.INVISIBLE
        binding?.weightLayout?.visibility=View.INVISIBLE

        binding?.foot?.text?.clear()
        binding?.inch?.text?.clear()
        binding?.pound?.text?.clear()
    }

    private fun displayBMIResults(bmi : Double){
        val bmiType :String

        if (bmi.compareTo(18.5)<=0) {
            bmiType = "Underweight"
        }else if (bmi.compareTo(18.5)>0 && bmi.compareTo(24.9)<=0){
            bmiType = "Normal weight"
        }else if (bmi.compareTo(24.9)>0 && bmi.compareTo(29.9)<=0){
            bmiType = "Overweight"
        }else if (bmi.compareTo(30)>0&& bmi.compareTo(39.9)<=0 ){
            bmiType = "Obesity"
        }
        else{
            bmiType="Severe Obesity"
        }

        val bmiValue=BigDecimal(bmi)
            .setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.bmiValue?.text=bmiValue
        binding?.bmiType?.text=bmiType
    }

    private fun validateUserInput ():Boolean{
        var isValid= true
        if (binding?.height?.text.toString().isEmpty()){
            isValid=false
        } else if(binding?.weight?.text.toString().isEmpty()){
            isValid=false
        }
        return isValid
    }
}