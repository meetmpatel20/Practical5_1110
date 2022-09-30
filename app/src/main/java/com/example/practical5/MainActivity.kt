package com.example.practical5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar


class MainActivity : AppCompatActivity() {

    lateinit var play_btn : ImageButton
    lateinit var stop_btn : ImageButton
    lateinit var ref_seekbar : SeekBar
    lateinit var forwardbutton : ImageButton
    lateinit var backwardbutton : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play_btn = findViewById(R.id.btn_play)
        stop_btn = findViewById(R.id.btn_stop)
        forwardbutton = findViewById(R.id.btn_skip2)
        backwardbutton = findViewById(R.id.btn_skip1)

        var count = 0
        play_btn.setOnClickListener {
            count++
            Intent(applicationContext, MyService::class.java).putExtra(
                MyService.Service1,
                MyService.PlayPause
            ).apply {
                startService(this)
            }
            if (count % 2 != 0) {
                play_btn.setImageResource(R.drawable.ic_baseline_pause_24)
            } else {
                play_btn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }
        stop_btn.setOnClickListener {
            Intent(applicationContext, MyService::class.java).apply {
                stopService(this)
            }
        }

        forwardbutton.setOnClickListener {
            Intent(applicationContext, MyService::class.java).putExtra(
                MyService.Service1,
                MyService.ForWard
            ).apply {
                startService(this)
            }
        }
        backwardbutton.setOnClickListener {
            Intent(applicationContext, MyService::class.java).putExtra(
                MyService.Service1,
                MyService.BackWard
            ).apply {
                startService(this)
            }
        }
    }
}