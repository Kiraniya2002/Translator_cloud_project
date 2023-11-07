package com.example.translator_app.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.translator_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslationTextField(textToBeTranslated: MutableState<String>) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 64.dp, 16.dp, 16.dp)
            .border(0.5.dp, Color.White),
        value = textToBeTranslated.value,
        onValueChange = {
            textToBeTranslated.value = it
        },
        singleLine = false,
        label = { Text(stringResource(id = R.string.et_translation)) },
        colors = TextFieldDefaults.textFieldColors(
            Color.White,
            unfocusedLabelColor = Color.White
        )
    )
}