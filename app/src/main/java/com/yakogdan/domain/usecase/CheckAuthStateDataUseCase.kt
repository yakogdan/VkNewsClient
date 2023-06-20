package com.yakogdan.domain.usecase

import com.yakogdan.domain.repository.NewsFeedRepository
import javax.inject.Inject

class CheckAuthStateDataUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}