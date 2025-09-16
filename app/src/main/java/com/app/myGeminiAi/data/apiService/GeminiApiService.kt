package com.app.myGeminiAi.data.apiService

import com.app.myGeminiAi.data.model.GeminiRequest
import com.app.myGeminiAi.data.model.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GeminiApiService {
    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    suspend fun askGemini(
        @Body request: GeminiRequest,
        @Query("key") apiKey: String = "AIzaSyBLEXbTgeUGdXFADN3xq8SazMxvBKMWh30"
    ): GeminiResponse
}