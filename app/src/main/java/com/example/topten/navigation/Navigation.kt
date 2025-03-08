package com.example.topten.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.topten.ui.screens.AllTasksScreen
import com.example.topten.ui.screens.GameScreen
import com.example.topten.ui.screens.HomeScreen
import com.example.topten.ui.screens.TaskSelectionScreen
import com.example.topten.ui.GameViewModel

object Routes {
    const val HOME = "home"
    const val TASK_SELECTION = "taskSelection"
    const val ALL_TASKS = "allTasks"
    const val GAME_SCREEN = "gameScreen"
}

@Composable
fun AppNavHost(navController: NavHostController) {

    val gameViewModel: GameViewModel = viewModel()
    //gameViewModel.loadTasks()
    //gameViewModel.loadTasksFromResource(LocalContext.current)

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) { HomeScreen(navController, gameViewModel) }
        composable(Routes.TASK_SELECTION) { TaskSelectionScreen(navController, gameViewModel) }
        composable(Routes.ALL_TASKS) { AllTasksScreen(navController, gameViewModel) }
        composable(Routes.GAME_SCREEN) { GameScreen(navController, gameViewModel) }
    }
}