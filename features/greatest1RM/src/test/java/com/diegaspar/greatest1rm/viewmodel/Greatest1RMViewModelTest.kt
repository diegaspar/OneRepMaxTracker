package com.diegaspar.greatest1rm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.diegaspar.data_layer.model.OneRepMaxDomain
import com.diegaspar.greatest1rm.domain.usecase.GetListOfExercisesRepMax
import com.diegaspar.greatest1rm.presentation.mapper.OneRepMaxDomainToUIMapper
import com.diegaspar.greatest1rm.presentation.state.Greatest1RMListState
import com.diegaspar.greatest1rm.presentation.state.LoadingState
import com.diegaspar.greatest1rm.presentation.state.SuccessState
import com.diegaspar.greatest1rm.presentation.viewmodel.Greatest1RMViewModel
import com.diegaspar.test.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class Greatest1RMViewModelTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mapper = OneRepMaxDomainToUIMapper()
    private val getListOfExercisesRepMax = mock<GetListOfExercisesRepMax>()
    private val stateObserver: Observer<Greatest1RMListState> = mock()
    private lateinit var viewModel: Greatest1RMViewModel

    @Before
    fun setUp() {
        viewModel = Greatest1RMViewModel(getListOfExercisesRepMax, mapper)
        viewModel.liveState.observeForever(stateObserver)
    }

    @Test
    fun `should call loading state when getOneRepMaxData is triggered`() = runTest {
        // Given
        val oneRepMaxList = listOf(
            OneRepMaxDomain(date = anyDate, name = anyName, oneRepMax = repMax),
            OneRepMaxDomain(date = anyDate, name = anyName, oneRepMax = repMax)
        )
        whenever(getListOfExercisesRepMax.invoke()).thenReturn(oneRepMaxList)

        // When
        viewModel.getOneRepMaxData()

        // Then
        verify(stateObserver).onChanged(LoadingState)
    }

    @Test
    fun `should get SuccessState when getOneRepMaxData is triggered and the response is successful`() =
        runTest {
            // Given
            val oneRepMaxList = listOf(
                OneRepMaxDomain(date = anyDate, name = anyName, oneRepMax = repMax),
                OneRepMaxDomain(date = anyDate, name = anyName, oneRepMax = repMax)
            )
            whenever(getListOfExercisesRepMax.invoke()).thenReturn(oneRepMaxList)

            // When
            viewModel.getOneRepMaxData()

            // Then
            inOrder(stateObserver).apply {
                verify(stateObserver).onChanged(LoadingState)
                verify(stateObserver).onChanged(SuccessState(oneRepMaxList.map { mapper.map(it) }))
            }
        }

    companion object {
        const val anyDate = "Apr 11 2022"
        const val anyName = "Chest Exercise"
        const val repMax = 223
    }

}