package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class MainActivity : AppCompatActivity() {

    //TODO (Refactor to replace Thread code with coroutines)

    private val cakeImageView: ImageView by lazy {
        findViewById(R.id.imageView)
    }

    private val currentTextView: TextView by lazy {
        findViewById(R.id.currentTextView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.revealButton).setOnClickListener {
        }
        val scope = CoroutineScope(Job() + Dispatchers.Default)


        scope.launch {
            repeat(100) {
                (it).toString().run {
                    Log.d("Opacity", this)
                    withContext(Dispatchers.Main) {
                        currentTextView.text = this@run

                        currentTextView.text =
                            String.format(Locale.getDefault(), "Current opacity: %d", it)
                        cakeImageView.alpha = it / 100f

                    }
                }
                delay(50)
            }
        }
    }
}

