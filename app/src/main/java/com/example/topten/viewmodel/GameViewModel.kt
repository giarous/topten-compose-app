package com.example.topten.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.topten.R
import com.example.topten.config.GameConfig
import com.example.topten.data.allTasksListEnglish
import com.example.topten.network.singleTaskCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        //loadTasks()
        //loadTasksFromResource()
    }

    fun fetchTaskFromOpenAi() {
        singleTaskCall (){ response ->
            _uiState.update { currentState ->
                currentState.copy(
                    currentTask = response
                )
            }
        }
    }

    fun dismissDialogs() {
        _uiState.value = _uiState.value.copy(
            showEndRoundDialog = false,
            showGameOverDialog = false,
            showGameWonDialog = false,
            showTaskDialog = false
        )
    }

    fun toggleTaskDialog(show: Boolean){
        _uiState.value = _uiState.value.copy(showTaskDialog = show)
    }

    fun updateButtonStates() {
        val players = _uiState.value.numberOfPlayers.toInt()
        val totalButtons = 10 // Adjust based on your game
        _uiState.value = _uiState.value.copy(
            buttonStates = List(totalButtons) { index -> index < players }
        )
    }

    fun loadTasks() {
        val shuffledTasks = allTasksListEnglish.shuffled()
        _uiState.value = _uiState.value.copy(
            taskList = shuffledTasks,
            taskCounter = 0
        )
    }

    fun loadTasksFromResource(context: Context){
        val tasks = context.resources.getStringArray(R.array.task_list).toList()
        _uiState.value = _uiState.value.copy(
            taskList = tasks,
            taskCounter = 0
        )
    }

    fun updateNumberOfPlayers(newValue: Float) {
        _uiState.value = _uiState.value.copy(numberOfPlayers = newValue)
        updateButtonStates()
    }


    fun resetRound() {
        _uiState.value = _uiState.value.copy(
            taskCounter = _uiState.value.taskCounter + 1,
            lastNumber = 0,
            numbersClicked = 0,
            buttonStates = List(GameConfig.NUM_OF_PLAYERS) { index -> index < _uiState.value.numberOfPlayers.toInt() },
            selectedNumbers = "",
            showEndRoundDialog = false
        )
    }

    fun resetGameState(){
        val shuffledTasks = allTasksListEnglish.shuffled()
        _uiState.value = _uiState.value.copy(
            taskList = shuffledTasks,
            taskCounter = 0,
            currentRound = 1,
            lastNumber = 0,
            numbersClicked = 0,
            numberOfUnicorns = GameConfig.INITIAL_LIVES,
            selectedNumbers = "",
            buttonStates = List(GameConfig.NUM_OF_PLAYERS) { index -> index < _uiState.value.numberOfPlayers.toInt() },
            showGameOverDialog = false,
            showGameWonDialog = false,
            showEndRoundDialog = false,
            showTaskDialog = false
        )
    }

    fun changeTask() {
        val tasks = _uiState.value.taskList
        if (tasks.isEmpty()) return

        val newTaskCounter = (_uiState.value.taskCounter + 1) % tasks.size
        _uiState.update { currentState ->
            currentState.copy(
                taskCounter = newTaskCounter,
                currentTask = tasks[newTaskCounter]
            )
        }
    }

    fun updateChoice(choice: Boolean){
        _uiState.value = _uiState.value.copy(areNumbersMatchingPlayers = choice)
    }

    fun onButtonClicked(index: Int) {
        val currentState = _uiState.value
        val newSelectedNumbers = currentState.selectedNumbers + "${index + 1} "
        val newButtonStates = currentState.buttonStates.toMutableList().apply { this[index] = false }
        val newNumberOfUnicorns = if (index < currentState.lastNumber) {
            currentState.numberOfUnicorns - 1
        } else {
            currentState.numberOfUnicorns
        }
        val newLastNumber = index + 1
        val newNumbersClicked = currentState.numbersClicked + 1
        val showEndRound = newNumbersClicked == currentState.numberOfPlayers.toInt()
        val newCurrentRound = if (showEndRound) currentState.currentRound + 1 else currentState.currentRound

        val showGameWon = showEndRound && newCurrentRound >= GameConfig.MAX_ROUNDS
        val showGameOver = newNumberOfUnicorns <= 0

        _uiState.value = currentState.copy(
            selectedNumbers = newSelectedNumbers,
            buttonStates = newButtonStates,
            numberOfUnicorns = newNumberOfUnicorns,
            lastNumber = newLastNumber,
            numbersClicked = newNumbersClicked,
            currentRound = newCurrentRound, // Update the round here
            showEndRoundDialog = showEndRound && !showGameWon,
            showGameWonDialog = showGameWon,
            showGameOverDialog = showGameOver
        )
    }
}