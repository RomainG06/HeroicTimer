package com.example.heroictimer

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    var timerStarted = false
    val timer: Timer = Timer()
    var stopStartButton: Button? = null
    var themeButton: Button? = null
    var settingsButton: Button? = null
    var timerTask: TimerTask? = null
    var time = 30.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stopStartButton = findViewById(R.id.button_launch)
        themeButton = findViewById(R.id.button_theme)
        settingsButton = findViewById(R.id.button_settings)

        stopStartButton?.text = "09:00"
        stopStartButton?.setOnClickListener {
            if (!timerStarted) {
                startTimer()
                timerStarted = true
            }
            else {
                timerStarted = false
                timerTask?.cancel()
            }
        }

        themeButton?.setOnClickListener{
            // Put navigation to ThemeFragment
        }

        settingsButton?.setOnClickListener {
            // Put navigation to SettingsFragment
        }
    }

    fun launchstopTimer(view: View) {

    }
    private fun startTimer() {
        timerTask = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    time--
                    stopStartButton?.text = getTimerText()
                }
            }
        }
        timer.scheduleAtFixedRate(timerTask, 0, 1000)
    }

    private fun getTimerText(): String? {
        val rounded = Math.round(time).toInt()
        val seconds = rounded % 86400 % 3600 % 60
        val minutes = rounded % 86400 % 3600 / 60
        //val hours = rounded % 86400 / 3600
        return formatTime(seconds, minutes)
    }

    private fun formatTime(seconds: Int, minutes: Int): String? {
        return String.format(String.format(
            "%02d",
            minutes
        ) + " : " + String.format("%02d", seconds))
    }
}