package com.diegaspar.greatest1rm.di

import com.diegaspar.greatest1rm.data.Greatest1RMLocalDataSource
import com.diegaspar.greatest1rm.data.Greatest1RMLocalDataSourceImpl
import com.diegaspar.greatest1rm.data.mapper.FileToLocalDataBaseMapper
import com.diegaspar.greatest1rm.domain.Greatest1RMRepository
import com.diegaspar.greatest1rm.domain.Greatest1RMRepositoryImpl
import com.diegaspar.greatest1rm.domain.mapper.LocalDataBaseToDomainMapper
import com.diegaspar.greatest1rm.domain.usecase.GetListOfExercisesRepMax
import com.diegaspar.greatest1rm.presentation.Greatest1RMViewModel
import com.diegaspar.onerepmax.OneRepMaxCalculator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val greatest1RMListModule = module {
    viewModel { Greatest1RMViewModel(get()) }
    single { GetListOfExercisesRepMax(get()) }
    single<Greatest1RMRepository> { Greatest1RMRepositoryImpl(get(), get(), get(), get()) }
    single<Greatest1RMLocalDataSource> { Greatest1RMLocalDataSourceImpl(get()) }

    single { LocalDataBaseToDomainMapper() }
    single { FileToLocalDataBaseMapper(get()) }
    single { OneRepMaxCalculator() }
}