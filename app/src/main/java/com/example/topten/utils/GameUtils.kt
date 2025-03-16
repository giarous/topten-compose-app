package com.example.topten.utils

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


