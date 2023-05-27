package com.yakogdan.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yakogdan.vknewsclient.domain.model.FeedPost
import com.yakogdan.vknewsclient.domain.model.StatisticItem
import com.yakogdan.vknewsclient.ui.NavigationItem

class MainViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(5) {
            add(FeedPost(id = it))
        }
    }
    private val _feedPosts = MutableLiveData<List<FeedPost>>(sourceList)
    val feedPosts: LiveData<List<FeedPost>> = _feedPosts

    private val _selectedNavItem = MutableLiveData<NavigationItem>(NavigationItem.Home)
    val selectedNavItem: LiveData<NavigationItem> = _selectedNavItem

    fun selectNavItem(item: NavigationItem) {
        _selectedNavItem.value = item
    }
    fun updateCount(feedPost: FeedPost,item: StatisticItem) {
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        _feedPosts.value = oldPosts.apply {
            replaceAll {
            if (it.id == newFeedPost.id) {
                newFeedPost
            } else {
                it
            }
        }
        }
    }
    fun removeItem(feedPost: FeedPost) {
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        oldPosts.remove(feedPost)
        _feedPosts.value = oldPosts
    }
}
