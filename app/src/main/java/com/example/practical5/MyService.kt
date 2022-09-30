package com.example.practical5

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.view.LayoutInflater
import android.widget.ImageButton


class MyService : Service() {

    lateinit var mediaPlayer: MediaPlayer
    lateinit var play_btn : ImageButton
    val Tag = "MyService"

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        if (!this::mediaPlayer.isInitialized) {
            mediaPlayer = MediaPlayer.create(this, R.raw.song)
        }
        if (intent!=null) {
            val str = intent.getStringExtra("Service1")
            if (str == "play"){
                if (!mediaPlayer.isPlaying){
                    mediaPlayer.start()
                } else {
                    mediaPlayer.pause()
                }
            } else {
                mediaPlayer.stop()
                mediaPlayer = MediaPlayer.create(this, R.raw.song)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        mediaPlayer.stop()
        super.onDestroy()
    }
}

