package com.williammunsch.ironbody.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.williammunsch.ironbody.R
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel

class HistoryAdapter(private var dataSet: List<LiftingWorkoutModel>) :
        RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.lifting_row_item, viewGroup, false)

        return HistoryViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: HistoryViewHolder, position: Int) {
        val workoutModel: LiftingWorkoutModel = dataSet[position]
        viewHolder.bind(workoutModel)
    }


    // Return the size of the data set (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


    //Allows the observer to set the data list
    fun setDataList(workoutList: List<LiftingWorkoutModel>){
        dataSet = workoutList
        notifyDataSetChanged()
    }


    //TODO : Replace the findViewById with data binding? (Is this possible?)
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var dateTextView: TextView? = null
        private var liftItemsTextView: TextView? = null
        private var liftItemsWeightTextView: TextView? = null

        init {
            dateTextView = itemView.findViewById(R.id.date_textview)
            liftItemsTextView = itemView.findViewById(R.id.lift_items_textview)
            liftItemsWeightTextView = itemView.findViewById(R.id.lift_items_textview_weight)
        }

        fun bind(liftingWorkoutModel: LiftingWorkoutModel) {
            dateTextView?.text = liftingWorkoutModel.date.toString()
            liftItemsTextView?.text = liftingWorkoutModel.lift_name
            liftItemsWeightTextView?.text = liftingWorkoutModel.weight.toString()
        }


    }

}
