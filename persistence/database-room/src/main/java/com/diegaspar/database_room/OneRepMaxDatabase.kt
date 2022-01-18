package com.diegaspar.database_room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diegaspar.database_room.dao.OneRepMaxDao
import com.diegaspar.database_room.entinty.OneRepMaxEntity

const val roomVersion = 1

@Database(entities = [OneRepMaxEntity::class], version = roomVersion)
abstract class OneRepMaxDatabase : RoomDatabase() {
    abstract fun oneRepMaxDao(): OneRepMaxDao
}