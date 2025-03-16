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
    val areNumbersMatchingPlayers: Boolean = false,
    val currentTask: String = "SELECT AN <font color='#85CA18'> AI-GENERATED TASK</font> OR <font color='red'>ONE FROM ALREADY STORED</font>",
)
