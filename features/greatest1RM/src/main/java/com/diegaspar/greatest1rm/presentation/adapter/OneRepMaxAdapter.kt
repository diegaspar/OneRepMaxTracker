package com.diegaspar.greatest1rm.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.greatest1rm.R
import com.diegaspar.greatest1rm.presentation.model.OneRepMaxUI

/**
 * [RecyclerView.Adapter] that can display a [OneRepMaxUI].
 */
class OneRepMaxAdapter(
    private val values: List<OneRepMaxUI>,
    private val listener: ViewHolderListener
) : RecyclerView.Adapter<OneRepMaxAdapter.ViewHolder>() {

    interface ViewHolderListener {
        fun onItemClicked(name: String, oneRepMax: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_rep_max_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.exerciseName.text = item.name
        holder.oneRepMax.text = item.oneRepMax.toString()
        holder.root.setOnClickListener {
            listener.onItemClicked(item.name, item.oneRepMax)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val exerciseName: TextView = view.findViewById(R.id.exercise_name_text)
        val oneRepMax: TextView = view.findViewById(R.id.one_rep_max_value_text)
        val root: ConstraintLayout = view.findViewById(R.id.parent_root)
    }
}