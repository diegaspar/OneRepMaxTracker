package com.diegaspar.context.ext

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun Context?.newActivityInstance(
    className: String,
    vararg params: Pair<String, Any>
) = Intent().also {
    it.putExtras(bundleOf(*params)).addFlags(FLAG_ACTIVITY_NEW_TASK)
    this?.let { context -> it.setClassName(context, className) }
}


inline fun <reified T : Fragment> newFragmentInstance(vararg params: Pair<String, Any>): T =
    T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }