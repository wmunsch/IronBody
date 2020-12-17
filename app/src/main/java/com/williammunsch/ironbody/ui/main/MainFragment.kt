package com.williammunsch.ironbody.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.williammunsch.ironbody.R

/**
 * Fragment for the home page of the app where statistics and history are shown.
 */
class MainFragment : Fragment() {

    private lateinit var pageViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(MainViewModel::class.java).apply {
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        /*
        pageViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })
         */
        return root
    }

    companion object {

        /**
         * Returns a new instance of this fragment for the given section
         * number.
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