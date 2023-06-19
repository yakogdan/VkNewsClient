package com.yakogdan.domain.usecase

import com.yakogdan.domain.repository.NewsFeedRepository

class LoadNextDataUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        repository.loadNextData()
    }
}