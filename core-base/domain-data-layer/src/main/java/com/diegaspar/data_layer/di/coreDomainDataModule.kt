package com.diegaspar.data_layer.di

import com.diegaspar.data_layer.data.Greatest1RMLocalDataSource
import com.diegaspar.data_layer.data.Greatest1RMLocalDataSourceImpl
import com.diegaspar.data_layer.data.mapper.FileToLocalDataBaseMapper
import com.diegaspar.data_layer.mapper.LocalDataBaseToDomainMapper
import com.diegaspar.data_layer.repo.Greatest1RMRepository
import com.diegaspar.data_layer.repo.Greatest1RMRepositoryImpl
import com.diegaspar.onerepmax.OneRepMaxCalculator
import org.koin.dsl.module

val coreDomainDataModule = module {
    single<Greatest1RMRepository> { Greatest1RMRepositoryImpl(get(), get(), get(), get()) }
    single<Greatest1RMLocalDataSource> { Greatest1RMLocalDataSourceImpl(get()) }

    single { LocalDataBaseToDomainMapper() }
    single { FileToLocalDataBaseMapper(get()) }
    single { OneRepMaxCalculator() }
}