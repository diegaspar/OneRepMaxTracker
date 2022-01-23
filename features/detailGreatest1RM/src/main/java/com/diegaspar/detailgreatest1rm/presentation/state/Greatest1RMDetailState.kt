package com.diegaspar.detailgreatest1rm.presentation.state

import com.github.mikephil.charting.data.Entry

sealed class Greatest1RMDetailState

object LoadingState : Greatest1RMDetailState()
data class ErrorState(val errorMessage: String) : Greatest1RMDetailState()
data class SuccessState(val exercisesList: List<Entry>) : Greatest1RMDetailState()
