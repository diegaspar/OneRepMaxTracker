package com.diegaspar.greatest1rm.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegaspar.greatest1rm.databinding.FragmentGreatest1RMListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class Greatest1RMListFragment : Fragment() {
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
        viewModel.getDataFromFile()
    }

    companion object {
        @JvmStatic
        fun newInstance() = Greatest1RMListFragment()
    }
}