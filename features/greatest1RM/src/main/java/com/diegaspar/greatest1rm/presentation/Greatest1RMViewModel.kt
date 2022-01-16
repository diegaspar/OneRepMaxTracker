package com.diegaspar.greatest1rm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegaspar.persistence.data.WorkOutFileDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Greatest1RMViewModel(private val workOutFileDataSource: WorkOutFileDataSource) : ViewModel() {

    fun getDataFromFile() {
        viewModelScope.launch(Dispatchers.IO) {
            val a = workOutFileDataSource.extractWorkoutDataFromFile()
            a.size
        }
    }
}