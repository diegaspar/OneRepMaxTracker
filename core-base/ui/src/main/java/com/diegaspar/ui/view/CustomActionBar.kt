package com.diegaspar.ui.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.diegaspar.ui.R
import com.diegaspar.ui.databinding.CustomActionBarLayoutBinding
import com.diegaspar.ui.ext.gone
import com.diegaspar.ui.ext.visible

class CustomActionBar @JvmOverloads constructor(
    context: Context,
    atts: AttributeSet? = null,
    defStyleAttributeSet: Int = 0
) : LinearLayout(context, atts, defStyleAttributeSet) {

    private var binding: CustomActionBarLayoutBinding? = null

    init {
        binding = CustomActionBarLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        atts?.let {
            context.theme.obtainStyledAttributes(it, R.styleable.CustomActionBar, 0, 0).apply {
                try {
                    val title = getString(R.styleable.CustomActionBar_title)
                    val iconLeft = getDrawable(R.styleable.CustomActionBar_icon_left)
                    val iconRight = getDrawable(R.styleable.CustomActionBar_icon_right)
                    setupTitle(title)
                    setupIconLeft(iconLeft)
                    setupIconRight(iconRight)
                } finally {
                    recycle()
                }
            }
        }
    }

    fun setupTitle(title: String?) {
        if (title.isNullOrEmpty().not()) {
            binding?.actionBarTitleText?.visible()
            binding?.actionBarTitleText?.text = title
        } else {
            binding?.actionBarTitleText?.gone()
        }
    }

    fun setupIconLeftOnClickListener(action: (View) -> Unit) {
        binding?.leftIcon?.setOnClickListener(action)
    }

    private fun setupIconLeft(iconLeft: Drawable?) {
        setupDrawable(iconLeft, binding?.leftIcon)
    }

    private fun setupIconRight(iconRight: Drawable?) {
        setupDrawable(iconRight, binding?.rightIcon)
    }

    private fun setupDrawable(iconLeft: Drawable?, imageView: ImageView?) {
        if (iconLeft != null) {
            imageView?.visible()
            imageView?.setImageDrawable(iconLeft)
        } else {
            imageView?.gone()
        }
    }

}