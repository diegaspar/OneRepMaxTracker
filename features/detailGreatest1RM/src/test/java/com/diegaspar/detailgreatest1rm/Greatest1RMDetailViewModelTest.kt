package com.diegaspar.detailgreatest1rm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.diegaspar.data_layer.model.OneRepMaxDomain
import com.diegaspar.detailgreatest1rm.domain.GetRecordExercisesByName
import com.diegaspar.detailgreatest1rm.presentation.mapper.OneRepDomainToDetailUIMapper
import com.diegaspar.detailgreatest1rm.presentation.state.Greatest1RMDetailState
import com.diegaspar.detailgreatest1rm.presentation.state.LoadingState
import com.diegaspar.detailgreatest1rm.presentation.state.SuccessState
import com.diegaspar.detailgreatest1rm.presentation.viewmodel.Greatest1RMDetailViewModel
import com.diegaspar.test.CoroutineTestRule
import com.diegaspar.test.TestValues.anyDate
import com.diegaspar.test.TestValues.anyDateOlder
import com.diegaspar.test.TestValues.anyName
import com.diegaspar.test.TestValues.firstXEntryValue
import com.diegaspar.test.TestValues.firstYEntryValue
import com.diegaspar.test.TestValues.repMax
import com.diegaspar.test.TestValues.secondXEntryValue
import com.diegaspar.test.TestValues.secondYEntryValue
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class Greatest1RMDetailViewModelTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mapper = mock<OneRepDomainToDetailUIMapper>()
    private val getRecordExercisesByName = mock<GetRecordExercisesByName>()
    private val stateObserver: Observer<Greatest1RMDetailState> = mock()
    private lateinit var viewModel: Greatest1RMDetailViewModel

    @Before
    fun setUp() {
        viewModel = Greatest1RMDetailViewModel(getRecordExercisesByName, mapper)
        viewModel.liveState.observeForever(stateObserver)
    }

    @Test
    fun `should call loading state when getRecordExercisesByName is triggered with any exercise name`() =
        runTest {
            // Given
            val oneRepMaxList = listOf(
                OneRepMaxDomain(date = anyDate, name = anyName, oneRepMax = repMax),
                OneRepMaxDomain(date = anyDate, name = anyName, oneRepMax = repMax)
            )
            whenever(getRecordExercisesByName.invoke(GetRecordExercisesByName.Params(anyName))).thenReturn(
                oneRepMaxList
            )

            // When
            viewModel.getOneRepListData(anyName)

            // Then
            verify(stateObserver).onChanged(LoadingState)
        }

    @Test
    fun `should get SuccessState sorted by date state when getRecordExercisesByName is triggered and there is a successful response`() =
        runTest {
            // Given
            val expectedFirsEntry = Entry(firstXEntryValue, firstYEntryValue)
            val expectedSecondEntry = Entry(secondXEntryValue, secondYEntryValue)
            val firstOneRepMax = OneRepMaxDomain(date = anyDate, name = anyName, oneRepMax = repMax)
            val secondOneRepMax =
                OneRepMaxDomain(date = anyDateOlder, name = anyName, oneRepMax = repMax)
            val oneRepMaxList = listOf(firstOneRepMax, secondOneRepMax)
            whenever(getRecordExercisesByName.invoke(GetRecordExercisesByName.Params(anyName))).thenReturn(
                oneRepMaxList
            )
            whenever(mapper.map(firstOneRepMax)).thenReturn(expectedFirsEntry)
            whenever(mapper.map(secondOneRepMax)).thenReturn(expectedSecondEntry)

            // When
            viewModel.getOneRepListData(anyName)

            // Then
            argumentCaptor<Greatest1RMDetailState>().run {
                verify(stateObserver, times(2)).onChanged(this.capture())
                assert(firstValue is LoadingState)
                assert(secondValue is SuccessState)

                assert((secondValue as SuccessState).exercisesList.size == 2)
                assert((secondValue as SuccessState).exercisesList.first().x == firstXEntryValue)
                assert((secondValue as SuccessState).exercisesList.first().y == firstYEntryValue)
                assert((secondValue as SuccessState).exercisesList.last().x == secondXEntryValue)
                assert((secondValue as SuccessState).exercisesList.last().y == secondYEntryValue)
            }
        }

}