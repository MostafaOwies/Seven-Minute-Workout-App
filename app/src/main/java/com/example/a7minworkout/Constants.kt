package com.example.a7minworkout

object Constants {

    //TODO Create a constants list and adding the exercises to the list
    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val highKnees=ExerciseModel(
            1,"High Knees",
            R.drawable.high_knees,
            isCompleted = false,
            isSelected = false
            )
        exerciseList.add(highKnees)

        val squats = ExerciseModel(
            2,"Squats",
            R.drawable.squat,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(squats)

        val lunges =ExerciseModel(
            3,"Lunges",
            R.drawable.lunges,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(lunges)

        val oneLegSquats = ExerciseModel(
            4,"One Leg Squats",
            R.drawable.one_leg_squat,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(oneLegSquats)

        val tricepDip = ExerciseModel(
            5,"Tricep Dip on Chair",
            R.drawable.tricep_dip_on_chair,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(tricepDip)

        val pushUp = ExerciseModel(
            6,"Push Up",
            R.drawable.push_up,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUp)

        val shoulderTap = ExerciseModel(
            7,"Shoulder Tap",
            R.drawable.shouldertap,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(shoulderTap)

        val plank = ExerciseModel(
            8,"Plank",
            R.drawable.plank,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(plank)

        val crunches = ExerciseModel(
            9,"Crunches",
            R.drawable.crunches,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(crunches)

        val lyingLegRaise = ExerciseModel(
            10,"lying Leg Raise",
            R.drawable.lying_leg_raise,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(lyingLegRaise)

        val reverseCrunch = ExerciseModel(
            11,"Reverse Crunch",
            R.drawable.reverse_crunch,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(reverseCrunch)

        val heelTouch = ExerciseModel(
            12,"Heel Touchers",
            R.drawable.heel_toucher,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(heelTouch)


        return exerciseList
    }
}