package com.yakogdan.presentation.comment

import com.yakogdan.vknewsclient.domain.model.FeedPost
import com.yakogdan.vknewsclient.domain.model.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}