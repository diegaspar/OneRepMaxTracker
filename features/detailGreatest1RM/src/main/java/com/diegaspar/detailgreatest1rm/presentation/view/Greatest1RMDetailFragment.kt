package com.diegaspar.detailgreatest1rm.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegaspar.detailgreatest1rm.databinding.FragmentDetailBinding
import com.diegaspar.detailgreatest1rm.presentation.state.ErrorState
import com.diegaspar.detailgreatest1rm.presentation.state.Greatest1RMDetailState
import com.diegaspar.detailgreatest1rm.presentation.state.LoadingState
import com.diegaspar.detailgreatest1rm.presentation.state.SuccessState
import com.diegaspar.detailgreatest1rm.presentation.viewmodel.Greatest1RMDetailViewModel
import com.diegaspar.navigation.NavigationParams.EXERCISE_NAME
import com.diegaspar.navigation.NavigationParams.ONE_REP_MAX
import org.koin.androidx.viewmodel.ext.android.viewModel

class Greatest1RMDetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private val viewModel: Greatest1RMDetailViewModel by viewModel()

    private val exerciseName: String by lazy {
        arguments?.getString(EXERCISE_NAME).orEmpty()
    }
    private val oneRepMax: Int by lazy {
        arguments?.getInt(ONE_REP_MAX) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.liveState.observe(viewLifecycleOwner) { state -> render(state) }
        viewModel.getOneRepListData(exerciseName)
    }

    private fun render(state: Greatest1RMDetailState?) {
        when (state) {
            is ErrorState -> TODO()
            LoadingState -> TODO()
            is SuccessState -> state.exercisesList
            null -> TODO()
        }
    }

    private fun setupUI() {
        //TODO
    }
}