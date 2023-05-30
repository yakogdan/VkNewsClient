package com.yakogdan.vknewsclient.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yakogdan.vknewsclient.MainViewModel
import com.yakogdan.vknewsclient.domain.model.FeedPost
import com.yakogdan.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun HomeScreen(viewModel: MainViewModel, paddingValues: PaddingValues) {
    VkNewsClientTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)

            when (val currentState = screenState.value) {

                is HomeScreenState.Posts -> {
                    FeedPosts(
                        posts = currentState.posts,
                        viewModel = viewModel,
                        paddingValues = paddingValues
                    )
                }

                is HomeScreenState.Comments -> {
                    CommentsScreen(
                        feedPost = currentState.feedPost,
                        comments = currentState.comments,
                        onBackPressed = {
                            viewModel.closeComments()
                        }
                    )
                    BackHandler {
                        viewModel.closeComments()
                    }
                }

                is HomeScreenState.Initial -> {}
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPosts(
    posts: List<FeedPost>, viewModel: MainViewModel, paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues), contentPadding = PaddingValues(
            top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp
        ), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts, key = { it.id }) { feedPost ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.removeItem(feedPost)
            }
            SwipeToDismiss(modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                directions = setOf(DismissDirection.EndToStart),
                background = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .background(Color.Red.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Delete item",
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    }
                }) {
                PostCard(feedPost = feedPost, onLikeClickListener = { statisticItem ->
                    viewModel.updateCount(
                        feedPost = feedPost, item = statisticItem
                    )
                }, onShareClickListener = { statisticItem ->
                    viewModel.updateCount(
                        feedPost = feedPost, item = statisticItem
                    )
                }, onViewsClickListener = { statisticItem ->
                    viewModel.updateCount(
                        feedPost = feedPost, item = statisticItem
                    )
                }, onCommentClickListener = {
                    viewModel.showComments(feedPost)
                })
            }
        }
    }
}