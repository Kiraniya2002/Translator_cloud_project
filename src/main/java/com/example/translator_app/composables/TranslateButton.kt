package com.example.translator_app.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.translator_app.R

@Composable
fun TranslateButton(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(16.dp)) {
        Text(stringResource(id = R.string.btn_translation))
    }
}