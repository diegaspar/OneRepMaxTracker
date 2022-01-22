package com.diegaspar.greatest1rm.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegaspar.greatest1rm.databinding.FragmentGreatest1RMListBinding
import com.diegaspar.greatest1rm.presentation.adapter.OneRepMaxAdapter
import com.diegaspar.greatest1rm.presentation.model.OneRepMaxUI
import com.diegaspar.greatest1rm.presentation.state.ErrorState
import com.diegaspar.greatest1rm.presentation.state.Greatest1RMListState
import com.diegaspar.greatest1rm.presentation.state.LoadingState
import com.diegaspar.greatest1rm.presentation.state.SuccessState
import com.diegaspar.greatest1rm.presentation.viewmodel.Greatest1RMViewModel
import com.diegaspar.ui.gone
import com.diegaspar.ui.visible
import org.koin.androidx.viewmodel.ext.android.viewModel


class Greatest1RMListFragment : Fragment(), OneRepMaxAdapter.ViewHolderListener {
    private var _binding: FragmentGreatest1RMListBinding? = null
    private val binding get() = _binding
    private val viewModel: Greatest1RMViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGreatest1RMListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        viewModel.liveState.observe(viewLifecycleOwner) { state -> render(state) }
        viewModel.getOneRepMaxData()
    }

    private fun setupUI() {
        binding?.retryButton?.setOnClickListener {
            viewModel.getOneRepMaxData()
        }

    }

    private fun render(state: Greatest1RMListState?) {
        when (state) {
            is ErrorState -> showErrorState(state)
            LoadingState -> showLoadingState()
            is SuccessState -> showSuccessState(state.exercisesList)
        }
    }

    private fun showSuccessState(exercisesList: List<OneRepMaxUI>) {
        binding?.progressBar?.gone()
        binding?.retryButton?.gone()
        binding?.recyclerView?.visible()
        setupRecyclerView(exercisesList)
    }

    private fun setupRecyclerView(exercisesList: List<OneRepMaxUI>) {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = OneRepMaxAdapter(exercisesList, this@Greatest1RMListFragment)
        }
    }

    private fun showLoadingState() {
        binding?.progressBar?.visible()
        binding?.recyclerView?.gone()
        binding?.retryButton?.gone()
    }

    private fun showErrorState(state: ErrorState) {
        Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
        binding?.retryButton?.visible()
        binding?.progressBar?.gone()
        binding?.recyclerView?.gone()
    }

    override fun onItemClicked(name: String) {
        //TODO Go to detail Graph Activity
    }
}