package com.diegaspar.onerepmaxtracker

import android.app.Application
import com.diegaspar.asset.di.persistenceAssetModule
import com.diegaspar.database_room.di.databaseModule
import com.diegaspar.greatest1rm.di.greatest1RMListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class OneRepMaxTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@OneRepMaxTrackerApplication)
            modules(
                persistenceAssetModule,
                databaseModule,
                greatest1RMListModule
            )
        }
    }
}