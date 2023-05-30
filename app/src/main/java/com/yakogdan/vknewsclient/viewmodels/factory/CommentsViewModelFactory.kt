package com.yakogdan.vknewsclient.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yakogdan.vknewsclient.domain.model.FeedPost
import com.yakogdan.vknewsclient.viewmodels.CommentsViewModel

class CommentsViewModelFactory(private val feedPost: FeedPost) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost = feedPost) as T
    }
}