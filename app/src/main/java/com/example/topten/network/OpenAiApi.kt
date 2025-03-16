package com.example.topten.network

import com.example.topten.config.ApiKeys
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {
        @Headers(
                "Content-Type: application/json",
                "Authorization: Bearer ${ApiKeys.OPENAI_API_KEY}"  // Replace with actual key or inject securely
        )
        @POST("v1/chat/completions")
        fun getChatCompletion(@Body request: ChatRequest): Call<ChatResponse>
}