package com.example.translator_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel= viewModel()
){
    val state= viewModel.state.value
    val context= LocalContext.current
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)) {
        TextField(value = state.textToBeTranslated, onValueChange =
        {viewModel.onTextToBeTranslatedChange(it)})
        Spacer(modifier = Modifier.height(7.dp))
        Button(onClick = { viewModel.onTranslateButtonClick(text=state.textToBeTranslated,context=context) },
            enabled=state.isButtonEnabled) {
            Text(text = "Translate")
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = state.translatedText, color = Color.White)
    }
}