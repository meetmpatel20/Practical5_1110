package com.example.practical5

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService: Service() {
    companion object{
        val PlayPause="play/pause"
        val ForWard="forward"
        val BackWard="backward"
        val Service1="Service"
        var startTimeS=0;
        var finalTimeS=0;
        var currentTimeS=0;

    }
    private var startTime = 0
    private var finalTime = 0
    private val forwardTime = 5000
    private val backwardTime = 5000
    private lateinit var mediaPlayer: MediaPlayer
    private var handler = Handler()
    private var runnable= Runnable(){
        if (!this::mediaPlayer.isInitialized) {
            mediaPlayer = MediaPlayer.create(this, R.raw.song)
            currentTimeS = mediaPlayer.getCurrentPosition()
            Log.e("Current Time",""+ currentTimeS)
            playHandler()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!this::mediaPlayer.isInitialized){
            mediaPlayer=MediaPlayer.create(this,R.raw.song)
            finalTime = mediaPlayer.getDuration()
            startTime = mediaPlayer.getCurrentPosition()
            startTimeS=startTime;
            finalTimeS= finalTime;
        }
        if (intent!=null){
            val str1=intent.getStringExtra(Service1)
            if (str1==PlayPause){
                if (mediaPlayer.isPlaying){
                    mediaPlayer.pause()
                    pauseHandler()
                }
                else{
                    mediaPlayer.start()
                    playHandler()
                }
            }else if(str1== ForWard){
                val temp = startTime
                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime
                    mediaPlayer.seekTo( startTime)
                    Toast.makeText(applicationContext,"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext,"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show()
                }
            }else if(str1== BackWard){
                val temp = startTime
                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo( startTime)
                    Toast.makeText(getApplicationContext(),"You have Jumped backward 5 seconds",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            mediaPlayer.start()
            playHandler()
        }
        return START_STICKY
    }
    override fun onDestroy() {
        mediaPlayer.stop()
        super.onDestroy()
    }

    fun playHandler(){
        handler.postDelayed(runnable,1000)
    }
    fun pauseHandler(){
        handler.removeCallbacks(runnable)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}
