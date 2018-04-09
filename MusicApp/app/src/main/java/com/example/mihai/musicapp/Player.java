package com.example.mihai.musicapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mihai.musicapp.Model.Song;


import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class Player extends AppCompatActivity {

    Button playBtn;
    ImageView playlistBtn;
    SeekBar songBar, volumeBar;
    TextView timePassed, timeRemaining, songInfo;
    MediaPlayer mediaPlayer;
    int totalTime, audioKey;
    String artist, title;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    } else if (focusChange == AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        playBtn = findViewById(R.id.playBtn);
        playlistBtn = findViewById(R.id.playlistBtn);
        timePassed = findViewById(R.id.timePassed);
        timeRemaining = findViewById(R.id.remainingTime);
        songInfo = findViewById(R.id.songInfo);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            artist = bundle.getString("ARTIST");
            title = bundle.getString("TITLE");
            audioKey = bundle.getInt("KEY");

            releaseMediaPlayer();
        }
            Song song = new Song(artist, title, audioKey);

            int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                    audioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mediaPlayer = MediaPlayer.create(this, song.getAudio());
                mediaPlayer.start();
                songInfo.setText(String.format("%s - %s", artist, title));
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });
            }

        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo(0);
        mediaPlayer.setVolume(0.5f, 0.5f);
        totalTime = mediaPlayer.getDuration();


        songBar = findViewById(R.id.songBar);
        songBar.setMax(totalTime);
        songBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    songBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Thread to update position bare and time TextView's

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer != null) {
                    try {
                        Message message = new Message();
                        message.what = mediaPlayer.getCurrentPosition();
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

        volumeBar = findViewById(R.id.volumeBar);
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volumeNum = progress / 100f;
                mediaPlayer.setVolume(volumeNum, volumeNum);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    playBtn.setBackgroundResource(R.drawable.ic_pause_black_24dp);
                } else {
                    mediaPlayer.pause();
                    playBtn.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                }
            }
        });

        playlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();
                Intent intent = new Intent(Player.this, SongList.class);
                startActivity(intent);
                finish();
            }
        });

    }


    public String updateTimeView(int time) {
        String timeView = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeView = min + ":";
        if (sec < 10) {
            timeView += "0";
        }
        timeView += sec;

        return timeView;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;

            songBar.setProgress(currentPosition);

            String timeElapsed = updateTimeView(currentPosition);
            timePassed.setText(timeElapsed);

            String remainingTime = updateTimeView(totalTime - currentPosition);
            timeRemaining.setText("- " + remainingTime);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();

        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                playBtn.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                mediaPlayer.pause();
            }
        }
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

}
