package com.yakogdan.domain.usecase

import com.yakogdan.domain.repository.NewsFeedRepository
import com.yakogdan.domain.state.AuthState
import kotlinx.coroutines.flow.StateFlow

class GetAuthStateFlowUseCase(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}