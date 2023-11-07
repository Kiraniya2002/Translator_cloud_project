package com.example.translator_app.presentation.translate

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.translator_app.R
import com.example.translator_app.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _textToBeTranslatedState =
        mutableStateOf("")
    var textToBeTranslatedState: MutableState<String> = _textToBeTranslatedState

    private val _state =
        mutableStateOf<TranslationState>(TranslationState.BeforeTranslation)
    var state: State<TranslationState> = _state

    init {
        _state.value = TranslationState.BeforeTranslation
        repository.createRealTimeTextTranslator()
    }

    fun translate(
        context: Context,
        input: String,
    ) {
        _state.value = TranslationState.TranslationLoading
        if (input.isEmpty()) {
            Toast.makeText(context, context.getText(R.string.toast_empty_text_field), Toast.LENGTH_LONG)
                .show()
        } else {
            repository.translate(input, onSuccess = { translatedText ->
                _state.value = TranslationState.TranslationSuccess(translatedText)
            }, onFailure = { errorMessage ->
                _state.value = TranslationState.TranslationFail(errorMessage)
            })
        }
    }
}