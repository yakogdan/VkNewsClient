package com.yakogdan.presentation.comment

import android.app.Application
import androidx.lifecycle.ViewModel
import com.yakogdan.data.repository.NewsFeedRepository
import com.yakogdan.domain.model.FeedPost
import kotlinx.coroutines.flow.map

class CommentsViewModel(feedPost: FeedPost, application: Application) : ViewModel() {

    private val repository = NewsFeedRepository(application)

    val screenState = repository.getComments(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}