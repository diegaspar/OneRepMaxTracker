package com.diegaspar.core_base

sealed class Result<out R> { //TODO USE THIS ?
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}