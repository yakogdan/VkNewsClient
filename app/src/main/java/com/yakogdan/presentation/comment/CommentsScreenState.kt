package com.yakogdan.presentation.comment

import com.yakogdan.domain.model.FeedPost
import com.yakogdan.domain.model.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}