package com.diegaspar.database_room.di

import android.app.Application
import androidx.room.Room
import com.diegaspar.database_room.OneRepMaxDatabase
import com.diegaspar.database_room.dao.OneRepMaxDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val dataBaseName = "onerepmax_database"

val databaseModule = module {
    fun provideDatabase(application: Application): OneRepMaxDatabase {
        return Room.databaseBuilder(
            application,
            OneRepMaxDatabase::class.java,
            dataBaseName
        ).build()
    }

    fun provideOneRepMaxDao(database: OneRepMaxDatabase): OneRepMaxDao {
        return database.oneRepMaxDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideOneRepMaxDao(get()) }
}