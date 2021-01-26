package com.example.projectiot.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.projectiot.R;

public class AlarmMusic extends Service {

    MediaPlayer mediaPlayer;
    float co, lpg, smoke, humidity, temperature;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        co = intent.getExtras().getFloat("co");
        Log.v("MUSIC", String.valueOf(co));
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm2);
        if (co > 100) {
            mediaPlayer.start();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }
}
