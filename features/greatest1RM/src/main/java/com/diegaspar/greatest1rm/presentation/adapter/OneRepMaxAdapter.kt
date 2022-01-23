package com.diegaspar.greatest1rm.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.diegaspar.greatest1rm.R
import com.diegaspar.greatest1rm.presentation.model.OneRepMaxUI
import com.diegaspar.ui.view.OneRepMaxRow

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
        holder.oneRepMaxRow.setupExerciseName(item.name)
        holder.oneRepMaxRow.setupOneRepMaxValue(item.oneRepMax.toString())
        holder.root.setOnClickListener {
            listener.onItemClicked(item.name, item.oneRepMax)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val oneRepMaxRow: OneRepMaxRow = view.findViewById(R.id.oneRepMax_row)
        val root: ConstraintLayout = view.findViewById(R.id.parent_root)
    }
}