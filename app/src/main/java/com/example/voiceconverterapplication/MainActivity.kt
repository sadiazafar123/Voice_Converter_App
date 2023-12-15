package com.example.voiceconverterapplication

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import java.util.Objects


class MainActivity : AppCompatActivity() {
    private lateinit var iv_mic: ImageView
    private var tv_Speech_to_text: TextView? = null
    private val REQUEST_CODE_SPEECH_INPUT = 1
    private var currentLanguage = Locale.ENGLISH // Default language is English


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iv_mic = findViewById(R.id.iv_mic)
        tv_Speech_to_text = findViewById(R.id.tv_speech_to_text)
        iv_mic.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
//            intent.putExtra(
//                RecognizerIntent.EXTRA_LANGUAGE,
//                Locale.ENGLISH
//            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                "ur-PK"
            )

            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")


            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            }
            catch (e: Exception) {
                Toast
                    .makeText(
                        this@MainActivity, " " + e.message,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }


        }


    }
    override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )

                tv_Speech_to_text!!.text =
                    Objects.requireNonNull(result)?.get(0) !!
                Toast.makeText(this, "$result", Toast.LENGTH_SHORT).show()
                Log.d("text","$result")
            }
        }
    }

}