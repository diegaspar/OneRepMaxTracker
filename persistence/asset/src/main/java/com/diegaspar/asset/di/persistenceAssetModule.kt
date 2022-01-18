package com.diegaspar.asset.di

import com.diegaspar.asset.data.WorkOutFileDataSource
import com.diegaspar.asset.data.WorkOutFileDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistenceAssetModule = module {
    single<WorkOutFileDataSource> { WorkOutFileDataSourceImpl(androidContext()) }
}