package com.example.topten.ui

data class GameUiState(
    val taskList: List<String> = emptyList(),
    val taskCounter: Int = 0,
    val numberOfPlayers: Float = 6f,
    val numberOfUnicorns: Int = 8,
    val selectedNumbersList: List<Int> = emptyList(),
    val selectedNumbers: String = "",
    val buttonStates: List<Boolean> = List(10) { true },
    val lastNumber: Int = 0,
    val numbersClicked: Int = 0,
    val currentRound: Int = 1,
    val showEndRoundDialog: Boolean = false,
    val showGameOverDialog: Boolean = false,
    val showGameWonDialog: Boolean = false,
    val showTaskDialog: Boolean = false,
    val areNumbersMatchingPlayers: Boolean = false
)
