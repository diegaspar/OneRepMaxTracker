package com.diegaspar.detailgreatest1rm.presentation.state

import com.diegaspar.detailgreatest1rm.presentation.model.OneRepDetail

sealed class Greatest1RMDetailState

object LoadingState : Greatest1RMDetailState()
data class ErrorState(val errorMessage: String) : Greatest1RMDetailState()
data class SuccessState(val exercisesList: List<OneRepDetail>) : Greatest1RMDetailState()
