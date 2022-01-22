package com.diegaspar.detailgreatest1rm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegaspar.detailgreatest1rm.domain.GetRecordExercisesByName
import com.diegaspar.detailgreatest1rm.presentation.mapper.OneRepDomainToDetailUIMapper
import com.diegaspar.detailgreatest1rm.presentation.state.ErrorState
import com.diegaspar.detailgreatest1rm.presentation.state.Greatest1RMDetailState
import com.diegaspar.detailgreatest1rm.presentation.state.LoadingState
import com.diegaspar.detailgreatest1rm.presentation.state.SuccessState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Greatest1RMDetailViewModel(
    private val getRecordExercisesByName: GetRecordExercisesByName,
    private val mapper: OneRepDomainToDetailUIMapper
) : ViewModel() {


    private val _liveState = MutableLiveData<Greatest1RMDetailState>()
    val liveState: LiveData<Greatest1RMDetailState> = _liveState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _liveState.postValue(ErrorState(throwable.toString()))
    }

    fun getOneRepListData(exerciseName: String) {
        _liveState.postValue(LoadingState)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _liveState.postValue(
                SuccessState(
                    getRecordExercisesByName
                        .invoke(GetRecordExercisesByName.Params(exerciseName = exerciseName))
                        .map { mapper.map(it) })
            )
        }
    }
}