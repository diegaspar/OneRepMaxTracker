package com.diegaspar.greatest1rm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegaspar.greatest1rm.domain.usecase.GetListOfExercisesRepMax
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Greatest1RMViewModel(private val getListOfExercisesRepMax: GetListOfExercisesRepMax) :
    ViewModel() {

    fun getDataFromFile() {
        viewModelScope.launch(Dispatchers.IO) {
            val a = getListOfExercisesRepMax.invoke()
            a.size
        }
    }
}