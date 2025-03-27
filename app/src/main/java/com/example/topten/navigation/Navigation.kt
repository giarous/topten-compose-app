package com.example.topten.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.topten.ui.screens.AllTasksScreen
import com.example.topten.ui.screens.GameAiScreen
import com.example.topten.ui.screens.GameScreen
import com.example.topten.ui.screens.HomeScreen
import com.example.topten.ui.screens.TaskSelectionScreen
import com.example.topten.viewmodel.GameViewModel

object Routes {
    const val HOME_SCREEN = "homeScreen"
    const val ALL_TASKS_SCREEN = "allTasksScreen"
    const val TASK_SELECTION = "taskSelectionScreen"
    const val GAME_SCREEN = "gameScreen"
    const val GAME_AI_SCREEN = "gameAiScreen"
}

@Composable
fun AppNavHost(navController: NavHostController) {

    val gameViewModel: GameViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME_SCREEN
    ) {
        composable(Routes.HOME_SCREEN) { HomeScreen(navController, gameViewModel) }
        composable(Routes.ALL_TASKS_SCREEN) { AllTasksScreen(navController, gameViewModel) }
        composable(Routes.TASK_SELECTION) { TaskSelectionScreen(navController, gameViewModel) }
        composable(Routes.GAME_SCREEN) { GameScreen(navController, gameViewModel) }
        composable(Routes.GAME_AI_SCREEN) { GameAiScreen(navController, gameViewModel) }
    }
}