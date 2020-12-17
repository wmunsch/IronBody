package com.williammunsch.ironbody.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * Fragment for the workout page where data is entered during a workout.
 */
class WorkoutFragment : Fragment() {

    private lateinit var pageViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(MainViewModel::class.java).apply {
        }
    }

    companion object {

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): WorkoutFragment {
            return WorkoutFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}