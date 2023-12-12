package com.example.a02task02nuis

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private val REQ_CODE_SPEECH_INPUT = 100
    private lateinit var tvResult: TextView

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSpeak: ImageButton = findViewById(R.id.btnSpeak)
        tvResult = findViewById(R.id.textResult)

        btnSpeak.setOnClickListener {
            promptSpeechInput()
        }
    }

    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Habla algo...")

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (e: ActivityNotFoundException) {
            tvResult.text = "Error: Reconocimiento de voz no disponible"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                val result: ArrayList<String>? =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                var spokenText = result?.get(0) ?: ""
                tvResult.text = "Texto hablado: $spokenText"

                spokenText = spokenText.toLowerCase().toString()
                /*
                when(spokenText){
                    "google" ->{
                        val intent = packageManager.getLaunchIntentForPackage("com.android.chrome")
                        if (intent != null) {
                            startActivity(intent)
                        } else {
                            tvResult.text = "Error: Aplicación de Google no encontrada"
                        }
                    }
                    "calendario" ->{
                        val intent = packageManager.getLaunchIntentForPackage("com.google.android.calendar")
                        if (intent != null) {
                            startActivity(intent)
                        } else {
                            tvResult.text = "Error: Aplicación de Calendario no encontrada"
                        }
                    }
                    "calculadora" ->{
                        val intent = packageManager.getLaunchIntentForPackage("com.google.android.calculator")
                        if (intent != null) {
                            startActivity(intent)
                        } else {
                            tvResult.text = "Error: Aplicación de Calculadora no encontrada"
                        }
                    }
                    "maps" ->{
                        val intent = packageManager.getLaunchIntentForPackage("com.google.android.apps.maps")
                        if (intent != null) {
                            startActivity(intent)
                        } else {
                            tvResult.text = "Error: Aplicación de Maps no encontrada"
                        }
                    }
                    "gmail" ->{
                        val intent = packageManager.getLaunchIntentForPackage("com.google.android.gm")
                        if (intent != null) {
                            startActivity(intent)
                        } else {
                            tvResult.text = "Error: Aplicación de Gmail no encontrada"
                        }
                    }
                }

                 */

                if (spokenText.equals("google", ignoreCase = true)) {
                    val intent = packageManager.getLaunchIntentForPackage("com.android.chrome")
                    if (intent != null) {
                        startActivity(intent)
                    } else {
                        tvResult.text = "Error: Aplicación de Google no encontrada"
                    }
                } else {
                    if (spokenText.equals("calendario", ignoreCase = true)) {
                        val intent =
                            packageManager.getLaunchIntentForPackage("com.google.android.calendar")
                        if (intent != null) {
                            startActivity(intent)
                        } else {
                            tvResult.text = "Error: Aplicación de Calendario no encontrada"
                        }

                    } else {
                        if (spokenText.equals("calculadora", ignoreCase = true)) {
                            val intent =
                                packageManager.getLaunchIntentForPackage("com.google.android.calculator")
                            if (intent != null) {
                                startActivity(intent)
                            } else {
                                tvResult.text = "Error: Aplicación de Calculadora no encontrada"
                            }
                        } else {
                            if (spokenText.equals("maps", ignoreCase = true)) {
                                val intent =
                                    packageManager.getLaunchIntentForPackage("com.google.android.apps.maps")
                                if (intent != null) {
                                    startActivity(intent)
                                } else {
                                    tvResult.text = "Error: Aplicación de Maps no encontrada"
                                }
                            } else {
                                if (spokenText.equals("gmail", ignoreCase = true)) {
                                    val intent =
                                        packageManager.getLaunchIntentForPackage("com.google.android.gm")
                                    if (intent != null) {
                                        startActivity(intent)
                                    } else {
                                        tvResult.text = "Error: Aplicación de Gmail no encontrada"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

