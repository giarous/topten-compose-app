package com.example.topten.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.topten.R
import com.example.topten.navigation.Routes
import com.example.topten.ui.GameViewModel
import com.example.topten.ui.components.ButtonContainer
import com.example.topten.ui.components.CustomDialog
import com.example.topten.ui.components.DefaultButton
import com.example.topten.ui.components.FooterImage
import com.example.topten.ui.components.Header
import com.example.topten.ui.components.ScoreBoardContainer
import com.example.topten.ui.components.TaskDialog
import com.example.topten.ui.theme.TopTenDark
import com.example.topten.ui.theme.TopTenTheme

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GameScreen(navController: NavHostController, gameViewModel: GameViewModel) {

    val uiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header(
            R.drawable.star_icon_image,
            R.drawable.star_icon_image,
            stringResource(R.string.game_screen_header)
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = TopTenDark
        )

        Spacer(modifier = Modifier.weight(1f))

        ScoreBoardContainer(uiState.numberOfUnicorns)

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = TopTenDark
        )

        Spacer(modifier = Modifier.weight(1f))

        ButtonContainer(uiState.buttonStates, onButtonClicked = { index ->
            gameViewModel.onButtonClicked(index)
        })

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = TopTenDark
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.selected_numbers),
                color = Color.White,
                modifier = Modifier
            )

            Text(
                text = uiState.selectedNumbers,
                color = Color.White,
                modifier = Modifier
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = TopTenDark
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            DefaultButton(
                buttonText = stringResource(R.string.go_to_home),
                onClick = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            )

            DefaultButton(
                buttonText = stringResource(R.string.show_task),
                onClick = {
                    gameViewModel.toggleTaskDialog(true)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            )

            DefaultButton(
                buttonText = stringResource(R.string.end_round),
                onClick = {
                    gameViewModel.resetRound()
                    navController.navigate(Routes.TASK_SELECTION) {
                        popUpTo(Routes.GAME_SCREEN) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        FooterImage()

    }

    if (uiState.showEndRoundDialog) {
        CustomDialog(
            stringResource(R.string.dialog_round_end_title),
            stringResource(R.string.dialog_round_end_message),
            stringResource(R.string.dialog_round_end_button),
            stringResource(R.string.cancel),
            onConfirm = {
                gameViewModel.resetRound()
                navController.navigate(Routes.TASK_SELECTION) {
                    popUpTo(Routes.GAME_SCREEN) { inclusive = true }
                }
                gameViewModel.dismissDialogs()
            },
            onDismiss = {
                gameViewModel.dismissDialogs()
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            },

            )
    }


    if (uiState.showTaskDialog) {
        TaskDialog(
            stringResource(R.string.dialog_task_title),
            uiState.taskList[uiState.taskCounter],
            onConfirm = {
                gameViewModel.toggleTaskDialog(false)
            }
        )
    }

    if (uiState.showGameWonDialog) {
        CustomDialog(
            stringResource(R.string.dialog_win_title),
            stringResource(R.string.dialog_win_message),
            stringResource(R.string.ok),
            stringResource(R.string.cancel),
            onConfirm = {
                gameViewModel.dismissDialogs()
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.GAME_SCREEN) { inclusive = true }
                }
            },
            onDismiss = {
                gameViewModel.dismissDialogs()
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            },

            )
    }

    if (uiState.showGameOverDialog) {
        CustomDialog(
            stringResource(R.string.dialog_loss_title),
            stringResource(R.string.dialog_loss_message),
            stringResource(R.string.new_game),
            stringResource(R.string.cancel),
            onConfirm = {
                gameViewModel.dismissDialogs()
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.GAME_SCREEN) { inclusive = true }
                }
            },
            onDismiss = {
                gameViewModel.dismissDialogs()
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            },

            )
    }
}

@Preview
@Composable
fun GameLayoutPreview() {
    val mockViewModel: GameViewModel = viewModel()
    mockViewModel.updateButtonStates()
    TopTenTheme {
        GameScreen(navController = rememberNavController(), mockViewModel)
    }
}