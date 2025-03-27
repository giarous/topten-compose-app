package com.example.topten.ui.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.topten.R
import com.example.topten.navigation.Routes
import com.example.topten.viewmodel.GameViewModel
import com.example.topten.ui.components.DefaultButton
import com.example.topten.ui.theme.TopTenTheme


@Composable
fun HomeScreen(navController: NavHostController, gameViewModel: GameViewModel) {

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(horizontal = 60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.topten_logo),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .sizeIn(maxHeight = 150.dp)
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        )

        DefaultButton(
            onClick = {
                gameViewModel.resetGameState()
                navController.navigate(Routes.TASK_SELECTION) },
            buttonText = stringResource(R.string.start_game),
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(20.dp))

        DefaultButton(
            onClick = {
                navController.navigate(Routes.ALL_TASKS_SCREEN)
                gameViewModel.loadTasks()
                      },
            buttonText = stringResource(R.string.all_tasks),
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(20.dp))

        DefaultButton(
            { (context as? Activity)?.finish()},
            "Exit Game",
            Modifier
        )
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PreviewHomeScreenLayout() {
    TopTenTheme {
        val mockViewModel: GameViewModel = viewModel()
        HomeScreen(navController = rememberNavController(), mockViewModel)
    }
}

