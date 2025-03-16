package com.example.topten.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.topten.R
import com.example.topten.navigation.Routes
import com.example.topten.viewmodel.GameViewModel
import com.example.topten.ui.components.DefaultButton
import com.example.topten.ui.components.FooterImage
import com.example.topten.ui.components.Header
import com.example.topten.ui.components.SectionDivider
import com.example.topten.ui.components.TaskContainer
import com.example.topten.ui.theme.TopTenTheme


@Composable
fun AllTasksScreen(navController: NavHostController, gameViewModel: GameViewModel) {

    val uiState by gameViewModel.uiState.collectAsState()
    var localTaskCounter by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Header(
            R.drawable.topten_logo,
            R.drawable.topten_scale_logo,
            stringResource(R.string.all_tasks_screen_header)
        )

        SectionDivider(false, modifier = Modifier.weight(1f))

        TaskContainer(taskText = uiState.taskList[localTaskCounter])

        SectionDivider( modifier = Modifier.weight(1f))

        TaskContainer(
            taskText = uiState.taskList[localTaskCounter+1],
            true
        )

        SectionDivider(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, top = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultButton(
                buttonText = stringResource(R.string.go_to_home),
                onClick = {
                    navController.navigate(Routes.HOME){
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
                buttonText = stringResource(R.string.change_task),
                onClick = {
                    if(localTaskCounter < uiState.taskList.size-3){
                        localTaskCounter+=2

                    }else{
                        Toast.makeText(context, context.getString(R.string.no_more_tasks), Toast.LENGTH_SHORT).show()
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

@Preview(showBackground = false)
@Composable
fun AllTasksPreview() {
    TopTenTheme {
        val mockViewModel: GameViewModel = viewModel()
        mockViewModel.loadTasks()
        AllTasksScreen(navController = rememberNavController(), mockViewModel)
    }
}


