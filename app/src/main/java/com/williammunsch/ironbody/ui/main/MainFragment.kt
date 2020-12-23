package com.williammunsch.ironbody.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.williammunsch.ironbody.R
import com.williammunsch.ironbody.WorkoutApplication
import com.williammunsch.ironbody.databinding.FragmentMainBinding

/**
 * Fragment for the home page of the app where statistics and history are shown.
 */
class MainFragment : Fragment() {

    private lateinit var pageViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)//.apply {}
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)
        binding.mainviewmodel = pageViewModel

        return binding.root
    }

    companion object {
        /**
         * Returns a new instance of this fragment
         */
        @JvmStatic
        fun newInstance(): MainFragment {
            return MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }
}