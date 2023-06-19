package com.yakogdan.domain.usecase

import com.yakogdan.domain.model.FeedPost
import com.yakogdan.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetRecommendationUseCase(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendation()
    }
}