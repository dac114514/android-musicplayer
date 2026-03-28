package com.faster.aichat.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.faster.aichat.ui.screens.ChatScreen
import com.faster.aichat.ui.screens.HistoryScreen
import com.faster.aichat.ui.screens.SetupScreen
import com.faster.aichat.viewmodel.ChatViewModel

@Composable
fun AiChatApp(
    viewModel: ChatViewModel,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavHost(
        navController = navController,
        startDestination = "chat",
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth }
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth }
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth }
            )
        }
    ) {
        composable("chat") {
            ChatScreen(
                viewModel = viewModel,
                onNavigateToSetup = { navController.navigate("setup") },
                onNavigateToHistory = { navController.navigate("history") }
            )
        }

        composable("setup") {
            SetupScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("history") {
            HistoryScreen(
                viewModel = viewModel,
                onNavigateToChat = {
                    navController.popBackStack()
                    viewModel.newConversation()
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}