package com.example.topten.network

import com.example.topten.parsePlayerResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Message(
    val role: String,
    val content: String
)

data class ChatRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>,
    val temperature: Float = 1.0f,
    val max_tokens: Int = 2048,
    val top_p: Float = 1.0f,
    val frequency_penalty: Float = 0.0f,
    val presence_penalty: Float = 0.0f,

    //Deepseek Suggestion
    //val n: Int = 1,                // Number of responses
    //val response_format: Map<String, String> = mapOf("type" to "json_object")

)

data class ChatResponse(
    val id: String,
    val choices: List<Choice>
)

data class Choice(
    val message: MessageContent
)

data class MessageContent(
    val content: String
)

fun structuredOpenAiCall(fullPrompt: String, callback: (String) -> Unit) {

    // Create the request message list with the user's input
    val messages = listOf(
        //Message(role = "system", content = "You are a hot playful woman. You respond in a very sensual and explicit way"),
        //Message(role = "system", content = "You are a convict with a rough personality. You respond bluntly, with slang, and keep your answers gritty and guarded."),
        //Message(role = "system", content = selectedPersonality.description),
        Message(role = "user", content = fullPrompt)
    )

    // Build the request
    val request = ChatRequest(
        messages = messages
    )

    // Make the API call using Retrofit
    val call = RetrofitClient.instance.getChatCompletion(request)
    call.enqueue(object : Callback<ChatResponse> {
        override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
            if (response.isSuccessful) {
                val chatResponse = response.body()
                val apiResponse = chatResponse?.choices?.firstOrNull()?.message?.content
                if (apiResponse != null) {
                    val playerResponses = parsePlayerResponses(apiResponse)
                    if (playerResponses != null) {
                        // Now you have a map of responses keyed by player grade (1 to 10)
                        // You can update your UI or game logic accordingly
                        callback(apiResponse)
                        //callback(playerResponses.toString())
                    } else {
                        callback("Failed to parse JSON response")
                    }
                } else {
                    callback("No response from API")
                }
            } else {
                callback("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
            // Pass the failure message to the callback
            callback("Failed to make request: ${t.message}")
        }
    })
}

fun simpleOpenAiCall(userMessage: String, callback: (String) -> Unit) {

    //selectedPersonality = getRandomPersonality()
    // Create the request message list with the user's input
    val messages = listOf(
        //Message(role = "system", content = "You are a hot playful woman. You respond in a very sensual and explicit way"),
        //Message(role = "system", content = "You are a convict with a rough personality. You respond bluntly, with slang, and keep your answers gritty and guarded."),
        //Message(role = "system", content = selectedPersonality.description),
        Message(role = "user", content = userMessage)
    )

    // Build the request
    val request = ChatRequest(
        messages = messages
    )

    // Make the API call using Retrofit
    val call = RetrofitClient.instance.getChatCompletion(request)
    call.enqueue(object : Callback<ChatResponse> {
        override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
            if (response.isSuccessful) {
                val chatResponse = response.body()
                val apiResponse = chatResponse?.choices?.firstOrNull()?.message?.content
                // Pass the response content to the callback
                callback(apiResponse ?: "No response from API")
            } else {
                callback("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
            // Pass the failure message to the callback
            callback("Failed to make request: ${t.message}")
        }
    })

}

fun singleTaskCall(callback: (String) -> Unit) {

    //selectedPersonality = getRandomPersonality()
    // Create the request message list with the user's input
    val messages = listOf(
        //Message(role = "system", content = "You are a hot playful woman. You respond in a very sensual and explicit way"),
        //Message(role = "system", content = "You are a convict with a rough personality. You respond bluntly, with slang, and keep your answers gritty and guarded."),
        //Message(role = "system", content = selectedPersonality.description),
        Message(
            role = "user",
            content = PromptTemplates.NEW_TASK_OPENAI_PROMPT)
    )

    // Build the request
    val request = ChatRequest(
        messages = messages
    )

    // Make the API call using Retrofit
    val call = RetrofitClient.instance.getChatCompletion(request)
    call.enqueue(object : Callback<ChatResponse> {
        override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
            if (response.isSuccessful) {
                val chatResponse = response.body()
                val apiResponse = chatResponse?.choices?.firstOrNull()?.message?.content
                // Pass the response content to the callback
                callback(apiResponse ?: "No response from API")
            } else {
                callback("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
            // Pass the failure message to the callback
            callback("Failed to make request: ${t.message}")
        }
    })

}
