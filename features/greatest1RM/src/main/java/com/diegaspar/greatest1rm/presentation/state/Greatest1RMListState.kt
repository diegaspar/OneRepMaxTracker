package com.diegaspar.greatest1rm.presentation.state

import com.diegaspar.greatest1rm.presentation.model.OneRepMaxUI

sealed class Greatest1RMListState

object LoadingState : Greatest1RMListState()
data class ErrorState(val errorMessage: String) : Greatest1RMListState()
data class SuccessState(val exercisesList: List<OneRepMaxUI>) : Greatest1RMListState()
