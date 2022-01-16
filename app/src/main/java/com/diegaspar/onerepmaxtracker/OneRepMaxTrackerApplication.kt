package com.diegaspar.onerepmaxtracker

import android.app.Application
import com.diegaspar.greatest1rm.di.greatest1RMListModule
import com.diegaspar.persistence.persistenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class OneRepMaxTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@OneRepMaxTrackerApplication)
            modules(
                persistenceModule,
                greatest1RMListModule
            )
        }
    }
}