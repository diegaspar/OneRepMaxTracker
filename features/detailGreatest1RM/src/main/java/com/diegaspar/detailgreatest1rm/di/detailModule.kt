package com.diegaspar.detailgreatest1rm.di

import com.diegaspar.detailgreatest1rm.domain.GetRecordExercisesByName
import com.diegaspar.detailgreatest1rm.presentation.mapper.OneRepDomainToDetailUIMapper
import com.diegaspar.detailgreatest1rm.presentation.viewmodel.Greatest1RMDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel { Greatest1RMDetailViewModel(get(), get()) }
    single { OneRepDomainToDetailUIMapper() }
    single { GetRecordExercisesByName(get()) }
}