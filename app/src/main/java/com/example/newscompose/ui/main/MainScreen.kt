package com.example.newscompose.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newscompose.R
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.model.News
import com.example.newscompose.navigation.NEWS_ID_KEY
import com.example.newscompose.navigation.NavigationItem
import com.example.newscompose.ui.home.HomeRoute
import com.example.newscompose.ui.home.HomeViewModel
import com.example.newscompose.ui.newsDetails.NewsDetailsRoute
import com.example.newscompose.ui.newsDetails.NewsDetailsViewModel
import com.example.newscompose.ui.newsDetails.di.newsDetailsModule
import com.example.newscompose.ui.saved.SavedRoute
import com.example.newscompose.ui.saved.SavedViewModel
import com.example.newscompose.ui.search.SearchRoute
import com.example.newscompose.ui.search.SearchViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var showBottomBar by remember {
        mutableStateOf(true)
    }
    val showBackIcon = !showBottomBar
    val homeViewModel = getViewModel<HomeViewModel>()
    val savedViewModel = getViewModel<SavedViewModel>()
    val searchViewModel = getViewModel<SearchViewModel>()
    Scaffold(
        topBar = {
            TopBar(
                navigationIcon = {
                    if (showBackIcon) BackIcon(
                        onBackClick = {
                            showBottomBar = showBottomBar.not()
                            navController.popBackStack()
                        }
                    )
                }
            )
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.SavedDestination,
                        NavigationItem.SearchDestination
                    ),
                    onNavigateToDestination = {
                        navController.navigate(it.route) {
                            popUpTo(it.route) {
                                inclusive = true
                            }
                        }
                    },
                    currentDestination = navBackStackEntry?.destination
                )
        }
    ) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeRoute(
                        homeViewModel = homeViewModel,
                        onNavigateToNewsDetails = {
                            showBottomBar = showBottomBar.not()
                            navController.navigate(it)
                        }
                    )
                }
                composable(NavigationItem.SavedDestination.route) {
                    SavedRoute(
                        savedViewModel = savedViewModel,
                        onNavigateToNewsDetails = {
                            showBottomBar = showBottomBar.not()
                            navController.navigate(it)
                        }
                    )
                }
                composable(NavigationItem.SearchDestination.route){
                    SearchRoute(
                        searchViewModel = searchViewModel,
                        onNavigateToNewsDetails = {
                            showBottomBar = showBottomBar.not()
                            navController.navigate(it)
                        },

                    )
                }
                composable(
                    route = NavigationItem.NewsDetailsDestination.route,
                    arguments = listOf(navArgument(NEWS_ID_KEY) { type = NavType.LongType }),
                ) {
                    val id = it.arguments?.getLong(NEWS_ID_KEY)
                    val newsDetailsViewModel =
                        getViewModel<NewsDetailsViewModel>(parameters = { parametersOf(id) })
                    NewsDetailsRoute(newsDetailsViewModel = newsDetailsViewModel)
                }
            }
        }
    }
}

@Composable
private fun BackIcon(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            modifier = Modifier
                .padding(10.dp)
                .clickable(onClick = onBackClick),
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
            contentDescription = null,
        )
    }
}

@Composable
private fun TopBar(navigationIcon: @Composable (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.news))
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Image(painter = painterResource(id = R.drawable.news_logo), contentDescription = null, modifier = Modifier.padding(top = 10.dp, start = 10.dp, bottom = 10.dp))
            Text(text = "NEWS", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 12.5.dp, start = 10.dp, bottom = 10.dp, end = 10.dp), color = Color.Black)
        }
        if (navigationIcon != null) {
            navigationIcon()
        }
    }
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (NavigationItem) -> Unit,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSurface
    ) {
        destinations.forEach { destination ->
            BottomNavigationItem(
                currentDestination?.route == destination.route,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id =
                        if (currentDestination?.route == destination.route)
                            destination.selectedIconId
                        else
                            destination.unselectedIconId),
                            contentDescription =
                            if (currentDestination?.route == destination.route)
                                stringResource(id = R.string.home)
                            else {
                                stringResource(id = R.string.saved)
                                stringResource(id = R.string.search)
                            },
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
                        )
                        Text(text = stringResource(id = destination.labelId))
                    }
                },
                onClick = { onNavigateToDestination(destination) }
            )
        }
    }
}
