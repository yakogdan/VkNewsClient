package com.yakogdan.vknewsclient.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yakogdan.vknewsclient.MainViewModel
import com.yakogdan.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun MainScreen(viewModel: MainViewModel) {

    Scaffold(
        bottomBar = {
            BottomNavigation {
                Log.d("COMPOSE_TEST", "BottomNavigation")

                val selectedItemPosition = remember {
                    mutableStateOf(0)
                }

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectedItemPosition.value == index,
                        onClick = { selectedItemPosition.value = index },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) { paddingValues ->
        VkScreen(viewModel = viewModel, paddingValues = paddingValues)
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun VkScreen(viewModel: MainViewModel, paddingValues: PaddingValues) {
    VkNewsClientTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            val models = viewModel.feedPosts.observeAsState(listOf())
            LazyColumn(
                modifier = Modifier.padding(paddingValues), contentPadding = PaddingValues(
                    top = 16.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 72.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(models.value, key = { it.id }) { feedPost ->
                    val dismissState = rememberDismissState()
                    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                        viewModel.removeItem(feedPost)
                    }
                    SwipeToDismiss(
                        modifier = Modifier.animateItemPlacement(),
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
                        PostCard(
                            feedPost = feedPost,
                            onLikeClickListener = { statisticItem ->
                                viewModel.updateCount(
                                    feedPost = feedPost,
                                    item = statisticItem
                                )
                            },
                            onShareClickListener = { statisticItem ->
                                viewModel.updateCount(
                                    feedPost = feedPost,
                                    item = statisticItem
                                )
                            },
                            onViewsClickListener = { statisticItem ->
                                viewModel.updateCount(
                                    feedPost = feedPost,
                                    item = statisticItem
                                )
                            },
                            onCommentClickListener = { statisticItem ->
                                viewModel.updateCount(
                                    feedPost = feedPost,
                                    item = statisticItem
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}