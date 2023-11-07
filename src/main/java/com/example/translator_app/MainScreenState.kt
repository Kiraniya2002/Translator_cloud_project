package com.example.translator_app

data class MainScreenState(val textToBeTranslated:String="",
                           val translatedText:String="",
                           val fromLang:String="",
                           val toLang:String="",
                           val isButtonEnabled:Boolean=true)
