package com.app.myGeminiAi.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.myGeminiAi.data.model.ChatMessage
import com.app.myGeminiAi.repositories.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeminiViewmodel @Inject constructor(
    private val geminiRepository: GeminiRepository
) : ViewModel() {

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    fun collectGeminiAnswer(question: String) {
        viewModelScope.launch {
            _chatMessages.update { it + ChatMessage(question, isUser = true) }

            geminiRepository.getGeminiAnswer(question).collect { result ->
                val answer = result.getOrNull() ?: "Something went wrong"
                _chatMessages.update { it + ChatMessage(answer, isUser = false) }
            }
        }
    }

}