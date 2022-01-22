package com.diegaspar.database_room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diegaspar.database_room.entinty.OneRepMaxEntity

@Dao
interface OneRepMaxDao {

    @Query("SELECT COUNT(*) FROM onerepmaxentity")
    suspend fun count(): Int

    @Query("SELECT *, MAX(one_rep_max) FROM onerepmaxentity GROUP BY exercise_name ORDER BY exercise_name")
    suspend fun loadOneRepMaxForAllExercisesOrderByName(): List<OneRepMaxEntity>

    @Query("SELECT *, MAX(one_rep_max) FROM onerepmaxentity WHERE exercise_name LIKE :exerciseName GROUP BY date")
    suspend fun loadAllByExerciseName(exerciseName: String): List<OneRepMaxEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(oneRepMaxList: List<OneRepMaxEntity>)
}