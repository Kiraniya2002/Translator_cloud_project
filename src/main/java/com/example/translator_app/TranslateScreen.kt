package com.example.translator_app

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.translator_app.composables.TranslateButton
import com.example.translator_app.composables.TranslationResultText
import com.example.translator_app.composables.TranslationTextField
import com.example.translator_app.presentation.translate.TranslateViewModel
import com.example.translator_app.presentation.translate.TranslationState
import com.example.translator_app.ui.theme.darkBackground

@Composable
fun TranslateScreen() {
    val context = LocalContext.current.applicationContext
    val translateViewModel = hiltViewModel<TranslateViewModel>()
    val state = translateViewModel.state.value
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBackground)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Language Translator", modifier = Modifier
            .padding(top = 40.dp), fontSize = 30.sp)
        TranslationTextField(textToBeTranslated = translateViewModel.textToBeTranslatedState)
        TranslateButton {
            translateViewModel.translate(context, translateViewModel.textToBeTranslatedState.value)
        }
        when (state) {
            is TranslationState.TranslationLoading -> LinearProgressIndicator()
            is TranslationState.TranslationSuccess -> TranslationResultText(translationResult = state.translationResult)
            is TranslationState.TranslationFail -> Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
            else -> {
            }
        }
    }
}