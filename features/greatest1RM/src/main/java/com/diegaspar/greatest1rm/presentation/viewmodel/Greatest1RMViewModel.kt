package com.diegaspar.greatest1rm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegaspar.greatest1rm.domain.usecase.GetListOfExercisesRepMax
import com.diegaspar.greatest1rm.presentation.mapper.OneRepMaxDomainToUIMapper
import com.diegaspar.greatest1rm.presentation.state.ErrorState
import com.diegaspar.greatest1rm.presentation.state.Greatest1RMListState
import com.diegaspar.greatest1rm.presentation.state.LoadingState
import com.diegaspar.greatest1rm.presentation.state.SuccessState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Greatest1RMViewModel(
    private val getListOfExercisesRepMax: GetListOfExercisesRepMax,
    private val mapper: OneRepMaxDomainToUIMapper
) : ViewModel() {

    private val _liveState = MutableLiveData<Greatest1RMListState>()
    val liveState: LiveData<Greatest1RMListState> = _liveState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _liveState.postValue(ErrorState(throwable.toString()))
    }

    fun getOneRepMaxData() {
        _liveState.postValue(LoadingState)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _liveState.postValue(
                SuccessState(
                    getListOfExercisesRepMax.invoke().map { mapper.map(it) })
            )
        }
    }
}