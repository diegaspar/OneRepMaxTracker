package com.diegaspar.persistence

import com.diegaspar.persistence.data.WorkOutFileDataSource
import com.diegaspar.persistence.data.WorkOutFileDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistenceModule = module {
    single<WorkOutFileDataSource> { WorkOutFileDataSourceImpl(androidContext()) }
}