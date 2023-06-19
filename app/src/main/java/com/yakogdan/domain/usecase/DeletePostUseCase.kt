package com.yakogdan.domain.usecase

import com.yakogdan.domain.model.FeedPost
import com.yakogdan.domain.repository.NewsFeedRepository

class DeletePostUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        repository.deletePost(feedPost)
    }
}