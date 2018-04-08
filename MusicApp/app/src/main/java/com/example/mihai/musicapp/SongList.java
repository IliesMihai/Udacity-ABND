package com.example.mihai.musicapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mihai.musicapp.Model.Song;

import java.io.IOException;
import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class SongList extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        final ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Chris Brown", "High End ft. Future & Young Thug", R.raw.chrisbrown_highend));
        songs.add(new Song("Kendrick Lamar, SZA", "All The Stars", R.raw.kendricklamar_allthestars));
        songs.add(new Song("Sigrid", "Strangers", R.raw.sigrid_strangers));
        songs.add(new Song("MANT", "Mant Theme", R.raw.mant_manttheme));
        songs.add(new Song("Kideko, Tinie Tempah, Becky G", "Dum Dum", R.raw.tinietempah_dumdum));
        songs.add(new Song("G Eazy", "No Limit", R.raw.geazy_nolimit));
        songs.add(new Song("Purple Disco Machine", "Where We Belong", R.raw.purplediscomachine_wherewebelong));
        songs.add(new Song("Purple Disco Machine", "Song For O", R.raw.purplediscomachine_songforo));
        songs.add(new Song("Purple Disco Machine", "No Lips", R.raw.purplediscomachine_nolips));
        songs.add(new Song("Purple Disco Machine", "Yo", R.raw.purplediscomachine_yo));
        songs.add(new Song("Purple Disco Machine", "Drumatic", R.raw.purplediscomachine_drumatic));
        songs.add(new Song("Daft Punk", "Rinzler ( 1 7 8 8 - L / R E M I X )", R.raw.daftpunk_rinzler));
        songs.add(new Song("Gorgon City", "Motorola", R.raw.gorgoncity_motorola));

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = findViewById(R.id.songs_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songs.get(position);
                Intent intent = new Intent(SongList.this, Player.class);
                Bundle bundle = new Bundle();
                bundle.putString("ARTIST", song.getArtist());
                bundle.putString("TITLE", song.getSongTitle());
                bundle.putInt("KEY", song.getAudio());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();


            }
        });
    }
}
