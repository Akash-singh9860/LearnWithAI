package com.app.myGeminiAi.ui.screens

import android.graphics.Color
import android.util.TypedValue
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import io.noties.markwon.Markwon


@Composable
fun ChatBubble(text: String, isUser: Boolean) {
    val alignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart

    val backgroundColor = if (isUser)
        MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
    val context = LocalContext.current
    val markwon = remember { Markwon.create(context) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        contentAlignment = alignment
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = backgroundColor,
            tonalElevation = 3.dp,
            modifier = Modifier
                .widthIn(max = 300.dp)
                .padding(horizontal = 8.dp)
        ) {
            AndroidView(
                factory = {
                    TextView(it).apply {
                        setTextColor(Color.BLACK)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    }
                },
                update = { textView ->
                    markwon.setMarkdown(textView, text)
                },
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

