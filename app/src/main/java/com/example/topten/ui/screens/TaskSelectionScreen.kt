package com.example.topten.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.topten.R
import com.example.topten.navigation.Routes
import com.example.topten.network.PromptTemplates
import com.example.topten.network.structuredOpenAiCall
import com.example.topten.viewmodel.GameViewModel
import com.example.topten.ui.components.CustomSlider
import com.example.topten.ui.components.CustomSwitch
import com.example.topten.ui.components.DefaultButton
import com.example.topten.ui.components.FooterImage
import com.example.topten.ui.components.Header
import com.example.topten.ui.components.SectionDivider
import com.example.topten.ui.components.TaskContainer
import com.example.topten.ui.theme.TopTenTheme
import com.example.topten.ui.theme.White
import com.example.topten.utils.parsePlayerResponses

@Composable
fun TaskSelectionScreen(navController: NavHostController, gameViewModel: GameViewModel) {

    val uiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header(
            R.drawable.topten_logo,
            R.drawable.topten_scale_logo,
            stringResource(R.string.task_selection_screen_header)
        )

        SectionDivider(false, modifier = Modifier.weight(1f))

        TaskContainer(taskText = uiState.currentTask)

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.topten_scale_logo),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(10.dp))

            DefaultButton(
                buttonText = "App Task",
                onClick = { gameViewModel.changeTask() },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            )

            DefaultButton(
                buttonText = "AI Task",
                onClick = { gameViewModel.fetchTaskFromOpenAi() },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            )
        }

        SectionDivider(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.players_icon),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            CustomSlider(
                uiState.numberOfPlayers,
                onSliderPositionChange = { newPosition ->
                    gameViewModel.updateNumberOfPlayers(newPosition)//sliderPosition = newPosition
                })

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = uiState.numberOfPlayers.toInt().toString(),
                color = White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.match_cards),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            CustomSwitch(
                uiState.areNumbersMatchingPlayers,
                onCheckedChange = { newState ->
                    gameViewModel.updateChoice(newState)
                })

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Play against AI",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            CustomSwitch(
                uiState.playAgainstAI,
                onCheckedChange = { newState ->
                    gameViewModel.updateOpponent(newState)
                })

        }

        SectionDivider(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            DefaultButton(
                buttonText = stringResource(R.string.go_to_home),
                onClick = {
                    navController.navigate(Routes.HOME_SCREEN) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            )

            DefaultButton(
                buttonText = stringResource(R.string.start_round),
                onClick = {

                    if (uiState.playAgainstAI) {
                        structuredOpenAiCall(PromptTemplates.getFormattedPrompt(uiState.currentTask)) { response ->
                            val allResponses = parsePlayerResponses(response) ?: emptyMap()
                            gameViewModel.updatePlayers(allResponses)
                        }

                        navController.navigate(Routes.GAME_AI_SCREEN) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }

                    } else {
                        navController.navigate(Routes.GAME_SCREEN) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }

                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        FooterImage()

    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun TaskSelectionLayoutPreview() {
    TopTenTheme {
        val mockViewModel: GameViewModel = viewModel()
        mockViewModel.loadTasks()
        TaskSelectionScreen(navController = rememberNavController(), mockViewModel)
    }
}