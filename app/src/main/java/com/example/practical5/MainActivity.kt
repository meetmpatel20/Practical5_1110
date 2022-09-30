package com.example.practical5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var play_btn : ImageButton
    lateinit var stop_btn : ImageButton
    lateinit var ref_seekbar : SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play_btn = findViewById(R.id.btn_play)
        stop_btn = findViewById(R.id.btn_stop)
        ref_seekbar = findViewById(R.id.seekbar)

        play_btn.setOnClickListener {
            intent = Intent(this,MyService::class.java)
            intent.putExtra("Service1","play")
            play_btn.setImageResource(R.drawable.ic_baseline_pause_24)
            startService(intent)
        }

        stop_btn.setOnClickListener {
            intent = Intent(this,MyService::class.java)
            intent.putExtra("Service1","stop")
            play_btn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            startService(intent)
        }

    }
}