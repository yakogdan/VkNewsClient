package com.yakogdan.domain.usecase

import com.yakogdan.domain.model.FeedPost
import com.yakogdan.domain.model.PostComment
import com.yakogdan.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetCommentsUseCase(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}