package com.diegaspar.data_layer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegaspar.asset.data.WorkOutFileDataSource
import com.diegaspar.asset.model.WorkOutFromFile
import com.diegaspar.data_layer.data.Greatest1RMLocalDataSource
import com.diegaspar.data_layer.data.mapper.FileToLocalDataBaseMapper
import com.diegaspar.data_layer.mapper.LocalDataBaseToDomainMapper
import com.diegaspar.data_layer.repo.Greatest1RMRepositoryImpl
import com.diegaspar.onerepmax.OneRepMaxCalculator
import com.diegaspar.test.CoroutineTestRule
import com.diegaspar.test.TestValues.anyDate
import com.diegaspar.test.TestValues.anyName
import com.diegaspar.test.TestValues.anyReps
import com.diegaspar.test.TestValues.anyWeight
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class Greatest1RMRepositoryImplTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val localDataSource = mock<Greatest1RMLocalDataSource>()
    private val fileDataSource = mock<WorkOutFileDataSource>()
    private val oneRepMaxCalculator = OneRepMaxCalculator()
    private val fileToLocalDataBaseMapper = FileToLocalDataBaseMapper(oneRepMaxCalculator)
    private val localDataBaseToDomainMapper = LocalDataBaseToDomainMapper()

    private lateinit var repo: Greatest1RMRepositoryImpl

    @Before
    fun setUp() {
        repo = Greatest1RMRepositoryImpl(
            localDataSource,
            fileDataSource,
            fileToLocalDataBaseMapper,
            localDataBaseToDomainMapper
        )
    }

    @Test
    fun `should get the data from fileDataSource when localDataSource is empty when calling getOneRepMaxGroupedByExercise`() =
        runTest {
            // Given
            val anyWorkOutFromFile = WorkOutFromFile(
                date = anyDate,
                exerciseName = anyName,
                reps = anyReps,
                weight = anyWeight
            )
            val listOfWorkoutsFromFile = listOf(
                anyWorkOutFromFile
            )
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(localDataSource.loadOneRepMaxForAllExercisesOrderByName()).thenReturn(listOf())
            whenever(fileDataSource.extractWorkoutDataFromFile()).thenReturn(listOfWorkoutsFromFile)

            // When
            repo.getOneRepMaxGroupedByExercise()

            // Then
            verify(localDataSource).isEmpty()
            verify(localDataSource).insertAll(listOfWorkoutsFromFile
                .map { fileToLocalDataBaseMapper.map(it) })
            verify(localDataSource).loadOneRepMaxForAllExercisesOrderByName()
        }

    @Test
    fun `should not get the data from fileDataSource when localDataSource is filled and should get it directly from localDataSource when calling getOneRepMaxGroupedByExercise`() =
        runTest {
            // Given
            val anyWorkOutFromFile = WorkOutFromFile(
                date = anyDate,
                exerciseName = anyName,
                reps = anyReps,
                weight = anyWeight
            )
            val listOfWorkoutsFromFile = listOf(
                anyWorkOutFromFile
            )
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.loadOneRepMaxForAllExercisesOrderByName()).thenReturn(listOf())

            // When
            repo.getOneRepMaxGroupedByExercise()

            // Then
            verify(localDataSource).isEmpty()
            verify(localDataSource, never()).insertAll(listOfWorkoutsFromFile
                .map { fileToLocalDataBaseMapper.map(it) })
            verify(localDataSource).loadOneRepMaxForAllExercisesOrderByName()
        }
}