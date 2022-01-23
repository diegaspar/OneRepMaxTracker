package com.diegaspar.detailgreatest1rm.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.diegaspar.context.ext.newFragmentInstance
import com.diegaspar.detailgreatest1rm.R
import com.diegaspar.detailgreatest1rm.databinding.DetailActivityBinding
import com.diegaspar.navigation.NavigationParams.EXERCISE_NAME
import com.diegaspar.navigation.NavigationParams.ONE_REP_MAX

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailActivityBinding
    private val exerciseName: String? by lazy { getExerciseNameParam() }
    private val oneRepMax: Int by lazy { getOneRepMaxParam() }

    private fun getExerciseNameParam() = intent.getStringExtra(EXERCISE_NAME)
    private fun getOneRepMaxParam() = intent.getIntExtra(ONE_REP_MAX, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        if (savedInstanceState == null) {
            tryToAddFragmentDetail()
        }
    }

    private fun setupUI() {
        binding.customActionBar.setupIconLeftOnClickListener { finish() }
        binding.customActionBar.setupTitle(exerciseName)
    }

    private fun tryToAddFragmentDetail() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(
                R.id.fragment_container_view,
                newFragmentInstance<Greatest1RMDetailFragment>(
                    Pair(EXERCISE_NAME, exerciseName.orEmpty()),
                    Pair(ONE_REP_MAX, oneRepMax),
                ), Greatest1RMDetailFragment::class.java.simpleName
            )
        }
    }
}