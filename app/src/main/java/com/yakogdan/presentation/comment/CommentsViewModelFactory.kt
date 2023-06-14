package com.yakogdan.presentation.comment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yakogdan.domain.model.FeedPost

@Suppress("UNCHECKED_CAST")
class CommentsViewModelFactory(
    private val feedPost: FeedPost,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost = feedPost, application = application) as T
    }
}