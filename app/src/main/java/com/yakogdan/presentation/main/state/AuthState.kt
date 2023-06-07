package com.yakogdan.presentation.main.state

sealed class AuthState {
    object Authorized : AuthState()
    object NotAuthorized : AuthState()
    object Initial : AuthState()
}