package com.williammunsch.ironbody.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.williammunsch.ironbody.R
import com.williammunsch.ironbody.databinding.FragmentMainBinding

/**
 * Fragment for the workout page where data is entered during a workout.
 */
class WorkoutFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
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