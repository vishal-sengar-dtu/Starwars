package com.example.starwars.repository

sealed class NetworkResult<T>(private val data : T? = null, private val message : String? = null) {
    class Success<T>(data: T?) : NetworkResult<T>(data = data)
    class Error<T>(message: String?) : NetworkResult<T>(message = message)
    class Loading<T>() : NetworkResult<T>()
}