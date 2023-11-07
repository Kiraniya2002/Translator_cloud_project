package com.example.translator_app.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TranslationResultText(translationResult: String) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .border(BorderStroke(1.dp, color = Color.White))
    ) {
        Text(
            text = translationResult,
            modifier = Modifier
                .padding(16.dp),
            color = Color.White,
            fontSize = 14.sp,
        )
    }
}