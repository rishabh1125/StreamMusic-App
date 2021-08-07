package com.example.streammusicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SongAdapter.OnNoteListener {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    ArrayList<Song> songList = new ArrayList<>();
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.main);
        RecyclerView recyclerView = findViewById(R.id.SongsView);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Iterable<DataSnapshot> songs = snapshot.getChildren();
                for (DataSnapshot song : songs)
                {
                    append(song.getValue(Song.class));
                }
                setRecycler(recyclerView);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                t.setText(error.toString());
            }
        });


    }

    private void setRecycler(RecyclerView recyclerView) {
        //t.setText(t.getText()+":"+String.valueOf(songList.size()));
        recyclerView.setAdapter(new SongAdapter(songList,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void append( Song s)
    {
        this.songList.add(s);
        t = findViewById(R.id.main);
        //t.setText(String.valueOf(songList.size()));
    }

    @Override
    public void onNoteClick(int position) {
        Song song = songList.get(position);
        Intent intent = new Intent(this, SongPlay.class);
        intent.putExtra("name", song.getSongName() );
        intent.putExtra("artist", song.getSongArtist() );
        intent.putExtra("thumbnail", song.getImageUrl() );
        intent.putExtra("url",song.getSongUrl());
        startActivity(intent);

    }
}