package com.example.streammusicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class SongPlay extends AppCompatActivity {
    private static final String TAG = "SongPlay";
    Intent i;
    String song,singer,imgUrl,link;
    TextView nowSong,artist; ImageView img;
    MediaPlayer mp; SeekBar seekBar; Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_song);
        Intent i = getIntent();
        song = i.getStringExtra("name");
        singer = i.getStringExtra("artist");
        imgUrl = i.getStringExtra("thumbnail");
        link = i.getStringExtra("url");
        nowSong = (TextView) findViewById(R.id.nowSong);
        nowSong.setText(song);
        artist = (TextView) findViewById(R.id.nowArtist);
        artist.setText(singer);
        img = (ImageView) findViewById(R.id.nowThumb);
        Picasso.get().load(imgUrl).resize(250,250).into(img);
        mp=new MediaPlayer();
        seekBar = (SeekBar) findViewById(R.id.songSeek);
        button = (Button) findViewById(R.id.playpause);
        button.setOnClickListener(v -> {
            if (mp.isPlaying())
            {
                mp.pause();
                button.setText("Play");
            }
            else {
                mp.start();
                button.setText("Pause");
            }
        });

        try{
            mp.setDataSource(link);
            mp.prepare();
            mp.start();
        }
        catch(Exception e){e.printStackTrace();}
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
