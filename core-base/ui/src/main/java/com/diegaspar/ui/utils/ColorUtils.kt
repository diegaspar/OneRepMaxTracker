package com.diegaspar.ui.utils

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun provideSafeColor(context: Context?, @ColorRes colorInt: Int): Int =
    context?.let { ContextCompat.getColor(it, colorInt) } ?: Color.WHITE
