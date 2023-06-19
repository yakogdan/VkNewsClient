package com.yakogdan.presentation.comment

import android.app.Application
import androidx.lifecycle.ViewModel
import com.yakogdan.data.repository.NewsFeedRepositoryImpl
import com.yakogdan.domain.model.FeedPost
import com.yakogdan.domain.usecase.GetCommentsUseCase
import kotlinx.coroutines.flow.map

class CommentsViewModel(feedPost: FeedPost, application: Application) : ViewModel() {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}