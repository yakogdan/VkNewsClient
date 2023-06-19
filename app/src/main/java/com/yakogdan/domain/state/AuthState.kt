package com.yakogdan.domain.state

sealed class AuthState {
    object Authorized : AuthState()
    object NotAuthorized : AuthState()
    object Initial : AuthState()
}