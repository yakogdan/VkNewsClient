package com.yakogdan.vknewsclient.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yakogdan.vknewsclient.domain.model.FeedPost
import com.yakogdan.vknewsclient.navigation.AppNavGraph
import com.yakogdan.vknewsclient.navigation.rememberNavigationState

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(bottomBar = {
        BottomNavigation {

            val navBackStackEntry by
            navigationState.navHostController.currentBackStackEntryAsState()

            val currentRoute = navBackStackEntry?.destination?.route

            val items = listOf(
                NavigationItem.Home, NavigationItem.Favourite, NavigationItem.Profile
            )

            items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navigationState.navigateTo(item.screen.route)
                    },
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
    }) { paddingValues ->

        AppNavGraph(navHostController = navigationState.navHostController,
            homeScreenContent = {
                if (commentsToPost.value == null) {
                    HomeScreen(
                        paddingValues = paddingValues,
                        onCommentClickListener = {
                            commentsToPost.value = it
                        }
                    )
                } else {
                    CommentsScreen {
                        commentsToPost.value = null
                    }
                }
            },
            favouriteScreenContent = { TextCounter(name = "Favourite") },
            profileScreenContent = {
                TextCounter(name = "Profile")
            })
    }
}

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable() {
        mutableStateOf(0)
    }
    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count:$count",
        color = MaterialTheme.colors.onBackground,
        fontSize = 30.sp
    )
}