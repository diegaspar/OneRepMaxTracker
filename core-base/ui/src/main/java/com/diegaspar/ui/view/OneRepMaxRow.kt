package com.diegaspar.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.diegaspar.ui.R
import com.diegaspar.ui.databinding.OneRepMaxRowBinding

class OneRepMaxRow @JvmOverloads constructor(
    context: Context,
    atts: AttributeSet? = null,
    defStyleAttributeSet: Int = 0
) : LinearLayout(context, atts, defStyleAttributeSet) {

    private var binding: OneRepMaxRowBinding? = null

    init {
        binding = OneRepMaxRowBinding.inflate(LayoutInflater.from(context), this, true)
        atts?.let {
            context.theme.obtainStyledAttributes(it, R.styleable.OneRepMaxRow, 0, 0).apply {
                try {
                    val exerciseName = getString(R.styleable.OneRepMaxRow_exerciseName)
                    val oneRepMax = getString(R.styleable.OneRepMaxRow_oneRepMax)
                    setupExerciseName(exerciseName)
                    setupOneRepMaxValue(oneRepMax)
                } finally {
                    recycle()
                }
            }
        }
    }

    fun setupExerciseName(name: String?) {
        if (name.isNullOrEmpty().not()) {
            binding?.exerciseNameText?.text = name
        }
    }

    fun setupOneRepMaxValue(value: String?) {
        if (value.isNullOrEmpty().not()) {
            binding?.oneRepMaxValueText?.text = value
        }
    }
}