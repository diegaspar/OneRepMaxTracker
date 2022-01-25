package com.diegaspar.detailgreatest1rm

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.diegaspar.asset.di.persistenceAssetModule
import com.diegaspar.data_layer.di.coreDomainDataModule
import com.diegaspar.database_room.di.databaseModule
import com.diegaspar.detailgreatest1rm.di.detailModule
import com.diegaspar.detailgreatest1rm.presentation.state.ErrorState
import com.diegaspar.detailgreatest1rm.presentation.state.Greatest1RMDetailState
import com.diegaspar.detailgreatest1rm.presentation.state.LoadingState
import com.diegaspar.detailgreatest1rm.presentation.state.SuccessState
import com.diegaspar.detailgreatest1rm.presentation.viewmodel.Greatest1RMDetailViewModel
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailActivityTest : KoinTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val mockModule = module {
        viewModel { mockedViewModel }
    }

    @Mock
    private lateinit var mockedViewModel: Greatest1RMDetailViewModel

    @Mock
    private lateinit var state: LiveData<Greatest1RMDetailState>

    @Captor
    private lateinit var stateObserverCaptor: ArgumentCaptor<Observer<Greatest1RMDetailState>>

    @Before
    fun setup() {
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext<Application>())
            modules(
                persistenceAssetModule,
                databaseModule,
                coreDomainDataModule,
                detailModule
            )
        }
        loadKoinModules(mockModule)
        `when`(mockedViewModel.liveState).thenReturn(state)
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockModule)
        stopKoin()
    }

    @Test
    fun should_show_error_when_any_error_happened_from_viewModel() {
        greatest1RMList {
            verify(state).observe(
                ArgumentMatchers.any(LifecycleOwner::class.java),
                stateObserverCaptor.capture()
            )
            MainScope().launch {
                stateObserverCaptor.value.onChanged(ErrorState(anyError))
            }
            checkToastErrorAppeared()
            checkLoadingIsNotDisplayed()
            checkChartViewIsNotDisplayed()
            checkRowInfoViewIsDisplayed()
            validateRowInfoDisplayed()
        }
    }

    @Test
    fun should_show_loading_and_anything_else_when_loading_from_viewModel() {
        greatest1RMList {
            verify(state).observe(
                ArgumentMatchers.any(LifecycleOwner::class.java),
                stateObserverCaptor.capture()
            )
            MainScope().launch {
                stateObserverCaptor.value.onChanged(LoadingState)
            }
            checkLoadingIsDisplayed()
            checkChartViewIsNotDisplayed()
            checkRowInfoViewIsDisplayed()
            validateRowInfoDisplayed()
        }
    }

    @Test
    fun should_show_charts_and_top_row_when_success_happened_from_viewModel() {
        greatest1RMList {
            verify(state).observe(
                ArgumentMatchers.any(LifecycleOwner::class.java),
                stateObserverCaptor.capture()
            )
            MainScope().launch {
                stateObserverCaptor.value.onChanged(SuccessState(exercisesList = anyListOfEntries))
            }
            checkLoadingIsNotDisplayed()
            checkChartViewIsDisplayed()
            checkRowInfoViewIsDisplayed()
            validateRowInfoDisplayed()
        }
    }

    companion object {
        internal const val anyExerciseName = "Chest"
        internal const val anyRepMaxValue = 111
        private const val x = 222f
        private const val y = 222f
        const val anyError = "error"
        val anyListOfEntries = listOf(
            Entry(x, y),
            Entry(x, y)
        )
    }
}