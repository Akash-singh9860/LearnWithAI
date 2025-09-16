package com.app.myGeminiAi.repositories

import com.app.myGeminiAi.data.apiService.GeminiApiService
import com.app.myGeminiAi.data.model.Content
import com.app.myGeminiAi.data.model.GeminiRequest
import com.app.myGeminiAi.data.model.Part
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GeminiRepository @Inject constructor(
    private val geminiApiService: GeminiApiService
) {
    fun getGeminiAnswer(question: String): Flow<Result<String>> = flow {
        try {
            val request = GeminiRequest(
                contents = listOf(Content(parts = listOf(Part(text = question))))
            )
            val response = geminiApiService.askGemini(request)
            val answer = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
            emit(Result.success(answer ?: "No answer available"))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

}