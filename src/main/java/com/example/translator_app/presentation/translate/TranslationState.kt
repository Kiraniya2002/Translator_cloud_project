package com.example.translator_app.presentation.translate

sealed class TranslationState{
    object BeforeTranslation: TranslationState()
    object TranslationLoading: TranslationState()
    class TranslationSuccess(val translationResult: String): TranslationState()
    class TranslationFail(val errorMessage: String): TranslationState()
}
