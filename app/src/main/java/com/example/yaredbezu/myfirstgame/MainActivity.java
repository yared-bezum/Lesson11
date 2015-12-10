package com.example.yaredbezu.myfirstgame;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    private MySurfaceView gp;
    private MediaPlayer song2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        gp = new MySurfaceView(this);

        Button musicOnButton = new Button(this);
        musicOnButton.setBackgroundResource(R.drawable.volumeon);
        musicOnButton.setPadding(0, 0, 0, 10);
        musicOnButton.setOnClickListener(musicOnButtonListener);
        Button musicOffButton = new Button(this);
        musicOffButton.setBackgroundResource(R.drawable.volumeoff);
        musicOffButton.setPadding(0, 0, 0, 10);
        musicOffButton.setOnClickListener(musicOffButtonListener);
        LinearLayout musicVolumeButtonLayout = new LinearLayout(this);
        musicVolumeButtonLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        musicVolumeButtonLayout.addView(musicOnButton);
        musicVolumeButtonLayout.addView(musicOffButton);

        FrameLayout outerLayout = new FrameLayout(this);
        outerLayout.addView(gp);
        outerLayout.addView(musicVolumeButtonLayout);
        setContentView(R.layout.activity_main);

    }
    public View.OnClickListener musicOnButtonListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View v)
        {
            if(!song2.isPlaying()) song2.start();
        }
    };

    public View.OnClickListener musicOffButtonListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View v)
        {
            if(song2.isPlaying()) song2.pause();
        }
    };
    public void allocateSounds()
    {
        if(song2 == null)
            song2 = MediaPlayer.create(this.getBaseContext(), R.raw.soccer);
        song2.setVolume(0.2f, 0.2f);
        song2.setOnPreparedListener(song2PListener);
        song2.setOnCompletionListener(song2CListener);
    }
    private MediaPlayer.OnPreparedListener song2PListener = new MediaPlayer.OnPreparedListener()
    {
        @Override
        public void onPrepared(MediaPlayer mp)
        {
            playSong2();
        }
    };

    private MediaPlayer.OnCompletionListener song2CListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mp)
        {
            playSong2();
        }
    };
    public void playSong2()
    {
        if (song2.isPlaying())
        {
            song2.pause();
        }
        if(song2.getCurrentPosition() != 1)
        {
            song2.seekTo(1);
        }
        song2.start();
    }

    public void deallocateSounds()
    {

        if (song2.isPlaying())
        {
            song2.stop();
        }

        if (!(song2 == null))
        {
            song2.release();
            song2 = null;
        }
        song2CListener = null;
        song2PListener = null;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        allocateSounds();

    }

    @Override
    protected void onPause()
    {
        deallocateSounds();
        super.onPause();

    }
}
