package com.example.agendamentoacademia.util

import android.content.Intent
import android.os.Build
import java.io.Serializable

/**
 * Extensão de compatibilidade para ler extras Serializable de uma Intent,
 * evitando o método depreciado em versões recentes do Android (API 33+)
 * sem quebrar a compatibilidade com versões anteriores (minSdk 24).
 */
inline fun <reified T : Serializable> Intent.getSerializableExtraCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getSerializableExtra(key) as? T
    }
}
