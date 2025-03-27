package com.example.topten.utils

import com.example.topten.data.Player
import org.json.JSONObject

fun parsePlayerResponses(apiResponse: String): Map<Int, String>? {
    return try {
        val jsonString = extractJsonFromResponse(apiResponse)
        val jsonObject = JSONObject(jsonString)
        val responses = mutableMapOf<Int, String>()
        jsonObject.keys().forEach { key ->
            responses[key.toInt()] = jsonObject.getString(key)
        }
        responses
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun extractJsonFromResponse(response: String): String {
    val startIndex = response.indexOf('{')
    val endIndex = response.lastIndexOf('}')
    return if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
        response.substring(startIndex, endIndex + 1)
    } else {
        throw IllegalArgumentException("No valid JSON object found in response")
    }
}

fun mergeResponsesWithPlayers(
    existingPlayers: List<Player>,
    responses: Map<Int, String>
): List<Player> {
    val shuffledEntries = responses.entries.shuffled()

    return existingPlayers.mapIndexed { index, player ->
        val responseEntry = shuffledEntries.getOrNull(index)
        player.copy(
            response = responseEntry?.value ?: "No response",
            level = responseEntry?.key ?: player.level,
            isActive = true
        )
    }
}

fun generateSamplePlayers(): List<Player> {
    val randomNames = listOf(
        "Alex", "Jordan", "Taylor", "Morgan", "Casey",
        "Riley", "Avery", "Peyton", "Quinn", "Skyler",
        "Dakota", "Charlie", "Emerson", "Finley", "Rowan",
        "Phoenix", "Harper", "Evelyn", "Liam", "Noah"
    ).shuffled()

    return List(10) { index ->
        Player(
            id = index + 1,
            name = randomNames[index],
            response = "Loading...",
            level = 1,
            isActive = true
        )
    }
}

fun resetPlayers(players: List<Player>): List<Player> {
    return players.map { player ->
        player.copy(
            response = "Loading...",
            level = 1,
            isActive = true
        )
    }
}


