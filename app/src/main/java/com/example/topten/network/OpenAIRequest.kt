package com.example.topten.network

import com.example.topten.utils.parsePlayerResponses
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
        Message(role = "user", content = fullPrompt)
    )

    val request = ChatRequest(
        messages = messages
    )

    val call = RetrofitClient.instance.getChatCompletion(request)
    call.enqueue(object : Callback<ChatResponse> {
        override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
            if (response.isSuccessful) {
                val chatResponse = response.body()
                val apiResponse = chatResponse?.choices?.firstOrNull()?.message?.content
                if (apiResponse != null) {
                    val playerResponses = parsePlayerResponses(apiResponse)
                    if (playerResponses != null) {
                        callback(apiResponse)
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
            callback("Failed to make request: ${t.message}")
        }
    })
}

fun simpleOpenAiCall(userMessage: String, callback: (String) -> Unit) {

    val messages = listOf(
        Message(role = "user", content = userMessage)
    )

    val request = ChatRequest(
        messages = messages
    )

    val call = RetrofitClient.instance.getChatCompletion(request)
    call.enqueue(object : Callback<ChatResponse> {
        override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
            if (response.isSuccessful) {
                val chatResponse = response.body()
                val apiResponse = chatResponse?.choices?.firstOrNull()?.message?.content
                callback(apiResponse ?: "No response from API")
            } else {
                callback("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
            callback("Failed to make request: ${t.message}")
        }
    })

}

fun singleTaskCall(callback: (String) -> Unit) {

    val messages = listOf(

        Message(
            role = "user",
            content = PromptTemplates.NEW_TASK_OPENAI_PROMPT)
    )

    val request = ChatRequest(
        messages = messages
    )

    val call = RetrofitClient.instance.getChatCompletion(request)
    call.enqueue(object : Callback<ChatResponse> {
        override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
            if (response.isSuccessful) {
                val chatResponse = response.body()
                val apiResponse = chatResponse?.choices?.firstOrNull()?.message?.content
                callback(apiResponse ?: "No response from API")
            } else {
                callback("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
            callback("Failed to make request: ${t.message}")
        }
    })

}
