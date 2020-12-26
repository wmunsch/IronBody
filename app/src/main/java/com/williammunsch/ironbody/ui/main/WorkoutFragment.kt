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
import com.williammunsch.ironbody.databinding.FragmentWorkoutBinding

/**
 * Fragment for the workout page where data is entered during a workout.
 */
class WorkoutFragment : Fragment() {

    private lateinit var pageViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentWorkoutBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_workout, container, false)
        binding.mainviewmodel = pageViewModel
        binding.lifecycleOwner = this
        return binding.root
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