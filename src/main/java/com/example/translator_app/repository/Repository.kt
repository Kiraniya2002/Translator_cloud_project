package com.example.translator_app.repository

import com.huawei.hms.mlsdk.common.MLException
import com.huawei.hms.mlsdk.translate.MLTranslatorFactory
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslateSetting
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslator

class Repository {
    private var mlRemoteTranslator: MLRemoteTranslator? = null

    fun createRealTimeTextTranslator() {
        // Create an offline translator.
        val setting =
            MLRemoteTranslateSetting.Factory()
                .setSourceLangCode("ta") // Set the source language
                // code, which complies with the ISO 639-1 standard. This parameter is mandatory. If this parameter is not set, an error may occur.
                .setTargetLangCode("en") // Set the target language
                // code, which complies with the ISO 639-1 standard. This parameter is mandatory. If this parameter is not set, an error may occur.
                .create()
        mlRemoteTranslator = MLTranslatorFactory.getInstance().getRemoteTranslator(setting)
    }

    fun translate(input: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        val translateTask = mlRemoteTranslator?.asyncTranslate(input)
        translateTask?.addOnSuccessListener { translatedText ->
            // Processing logic for recognition success.
            onSuccess(translatedText)
            // After translation is completed, release the detection resources:
            releaseTranslationResources()

        }?.addOnFailureListener { e ->
            // Processing logic for recognition failure.
            try {
                val mlException = e as MLException
                // Obtain the error information. You can quickly locate the fault based on the result code.
                val errorMessage = mlException.message
                errorMessage?.let {
                    onFailure(it)
                }
            } catch (error: java.lang.Exception) {
                error.localizedMessage?.let {
                    onFailure(it)
                }
            }
        }
    }

    private fun releaseTranslationResources() {
        mlRemoteTranslator?.stop()
    }
}