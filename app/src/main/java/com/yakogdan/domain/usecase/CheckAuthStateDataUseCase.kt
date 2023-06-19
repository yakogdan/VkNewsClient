package com.yakogdan.domain.usecase

import com.yakogdan.domain.repository.NewsFeedRepository

class CheckAuthStateDataUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}