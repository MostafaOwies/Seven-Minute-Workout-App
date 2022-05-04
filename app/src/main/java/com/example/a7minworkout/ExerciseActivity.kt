package com.example.a7minworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minworkout.databinding.ActivityExerciseBinding
import com.example.a7minworkout.databinding.ItemCustomDialogBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding :ActivityExerciseBinding?= null

    private var restTimer :CountDownTimer?=null
    private var restProgress = 0
    private var restProgressLimit = 10

    private var exerciseTimer :CountDownTimer?=null
    private var exerciseProgress=0
    private var exerciseProgressLimit = 30

    private var exerciseList :ArrayList<ExerciseModel>?=null
    private var currentExercisePosition = -1

    private var restProgressbarTimer : Long=10
    private var exerciseProgressbarTimer:Long=30

    private var tts : TextToSpeech?=null
    private var player : MediaPlayer?=null

    private var exerciseAdapter : ExerciseStatusAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //support the action bar
        setSupportActionBar(binding?.exerciseToolbar)
        if (supportActionBar !=null){
            // display the UP/Home button
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList=Constants.defaultExerciseList()
        tts=TextToSpeech(this,this)
        // set an OnClickListener to the navigation buttons
        binding?.exerciseToolbar?.setNavigationOnClickListener{
            customDialogBackButton()
        }

        setupRestView()
        setupExerciseStatusRV()
    }

    override fun onBackPressed() {
        customDialogBackButton()
    }

    private fun customDialogBackButton(){
        val customDialog = Dialog(this)
        //for binding a seperate XML file
        val dialogBinding=ItemCustomDialogBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        //dont allow user to cancer the dialog by touching out side
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.dialogYesBTN.setOnClickListener{
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }

        dialogBinding.dialogNoBTN.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun setupExerciseStatusRV(){
        binding?.RVexerciseNumber?.layoutManager=LinearLayoutManager(
            this,LinearLayoutManager.HORIZONTAL,false
        )
        exerciseAdapter=ExerciseStatusAdapter(exerciseList!!)
        binding?.RVexerciseNumber?.adapter=exerciseAdapter
    }
    private fun setupRestView(){
        /*try {
            player=MediaPlayer.create(applicationContext,R.raw.press_start)
            player?.isLooping=false
            player?.start()
        }catch (e:Exception){
            e.printStackTrace()
        }*/
        binding?.myFrameLayout?.visibility=View.VISIBLE
        binding?.myTitle?.visibility=View.VISIBLE
        binding?.exerciseFL?.visibility=View.GONE
        binding?.myExerciseTitle?.visibility=View.GONE
        binding?.exerciseImage?.visibility=View.GONE
        binding?.upcoming?.visibility=View.VISIBLE
        binding?.nextExerciseName?.visibility=View.VISIBLE
        if (restTimer !=null){
            restTimer?.cancel()
            restProgress=0
        }
        binding?.nextExerciseName?.text=exerciseList!![currentExercisePosition+1].getName()
        setRestProgressBar()
    }
    private fun setupExerciseView(){
        binding?.myFrameLayout?.visibility=View.GONE
        binding?.myTitle?.visibility=View.GONE
        binding?.exerciseFL?.visibility=View.VISIBLE
        binding?.myExerciseTitle?.visibility=View.VISIBLE
        binding?.exerciseImage?.visibility=View.VISIBLE
        binding?.upcoming?.visibility=View.GONE
        binding?.nextExerciseName?.visibility=View.GONE
        speakOut(exerciseList!![currentExercisePosition].getName())
        if (exerciseTimer !=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        binding?.exerciseImage?.setImageResource(
            exerciseList!![currentExercisePosition].getImage())
        binding?.myExerciseTitle?.text= exerciseList!![currentExercisePosition].getName()
        setExerciseProgress()
    }
    private fun setRestProgressBar(){
        //setting the progressbar progress to
        //the restProgress variable value
        binding?.progressBAR?.progress=restProgress
                                        //setting count down limit and count down interval
        restTimer=object :CountDownTimer(restProgressbarTimer*1000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBAR?.progress=restProgressLimit-restProgress
                binding?.timer?.text=(restProgressLimit-restProgress).toString()
            }
            override fun onFinish() {
                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter?.notifyDataSetChanged()

                setupExerciseView()
            }
        }.start()
    }
    private fun setExerciseProgress(){
        binding?.exerciseProgressbar?.progress=exerciseProgress
        exerciseTimer= object : CountDownTimer (exerciseProgressbarTimer*1000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.exerciseProgressbar?.progress=exerciseProgressLimit - exerciseProgress
                binding?.exerciseTimerTV?.text=(exerciseProgressLimit - exerciseProgress).toString()
            }
            override fun onFinish() {
                if(currentExercisePosition<exerciseList?.size!!-1){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "you finished your exercises",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                    val intent =Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }
    override fun onInit(status: Int) {
            if (status==TextToSpeech.SUCCESS){
                val result = tts?.setLanguage(Locale.US)

                if (result==TextToSpeech.LANG_MISSING_DATA||
                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(this,
                        "Language Not Supported!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else{
                Toast.makeText(this,
                    "Initialization Faild!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
    private fun speakOut(text: String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
        //
    }
    override fun onDestroy() {
        super.onDestroy()
        if (restTimer !=null){
            restTimer?.cancel()
            restProgress=0
        }
        if (exerciseTimer !=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        if (tts !=null){
            tts?.stop()
            tts?.shutdown()
        }
        if(player !=null){
            player?.stop()
        }
        binding=null
    }
}