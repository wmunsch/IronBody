package com.williammunsch.ironbody.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.williammunsch.ironbody.R
import com.williammunsch.ironbody.databinding.FragmentMainBinding
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel

/**
 * Fragment for the home page of the app where statistics and history are shown.
 */
class MainFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main, container, false)
        binding.mainviewmodel = mainViewModel
        binding.lifecycleOwner = this


        //Observe the list and pass it to the recycler view adapter so we can get the list from it
        val listObserver = Observer<List<LiftingWorkoutModel>> { newList ->
            adapter.setDataList(newList)
        }

        mainViewModel.historyListData.observe(viewLifecycleOwner, listObserver)


        //Create an initial empty list. The observer will change this asynchronously to show the database data
        val dataList: List<LiftingWorkoutModel> = emptyList()
        adapter = HistoryAdapter(dataList)

        binding.historyRecycler.adapter = adapter

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