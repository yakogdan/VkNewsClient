package com.yakogdan.domain.usecase

import com.yakogdan.domain.model.FeedPost
import com.yakogdan.domain.repository.NewsFeedRepository

class ChangeLikeStatusUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        repository.changeLikeStatus(feedPost)
    }
}