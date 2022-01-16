package com.diegaspar.greatest1rm.di

import com.diegaspar.greatest1rm.presentation.Greatest1RMViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val greatest1RMListModule = module {
    viewModel { Greatest1RMViewModel(get()) }
}