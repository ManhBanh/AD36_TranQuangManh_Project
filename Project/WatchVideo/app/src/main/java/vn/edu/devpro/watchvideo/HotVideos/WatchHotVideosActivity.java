package vn.edu.devpro.watchvideo.HotVideos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;

import vn.edu.devpro.watchvideo.MainActivity;
import vn.edu.devpro.watchvideo.R;

public class WatchHotVideosActivity extends AppCompatActivity {
    private static final String STATE_COUNTER = "counter";
    VideoView videoView;
    //    MediaController mediaController;
    HotVideos hotVideos;
    int counter;

    Toolbar tbWatchHotVideos;
    Button btnBack;
    ImageView imgPlay;
    TextView tvTitleHotVideos, tvStart, tvDuration;
    SeekBar sbHotVideos;
    ProgressBar pbLoadingWatchHotVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_hot_videos);

        if(savedInstanceState != null){
            counter = savedInstanceState.getInt(STATE_COUNTER, 0);
        }

        tbWatchHotVideos = findViewById(R.id.tbWatchHotVideos);
        btnBack = findViewById(R.id.btnBack);
        imgPlay = findViewById(R.id.imgPlay);
        tvTitleHotVideos = findViewById(R.id.tvTitleHotVideos);
        tvStart = findViewById(R.id.tvStart);
        tvDuration = findViewById(R.id.tvDuration);
        sbHotVideos = findViewById(R.id.sbHotVideos);
        videoView = findViewById(R.id.vvWatch);

        setSupportActionBar(tbWatchHotVideos);

        hotVideos = (HotVideos) getIntent().getSerializableExtra("hotvideos");

//         Tạo bộ điều khiển
//        if (mediaController == null) {
//            mediaController = new MediaController(WatchHotVideosActivity.this);
//
//            // Neo vị trí của MediaController với VideoView.
//            mediaController.setAnchorView(videoView);
//
//            // Sét đặt bộ điều khiển cho VideoView.
//            videoView.setMediaController(mediaController);
//        }

        createVideoView();
        videoView.start();
        updateTimeOnSeekBar();
        tvDuration.setText(String.valueOf(videoView.getDuration()));

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    imgPlay.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                } else {
                    videoView.start();
                    imgPlay.setImageResource(R.drawable.ic_pause_white_24dp);
                }
                setDuration();
                updateTimeOnSeekBar();
            }
        });

        sbHotVideos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                videoView.seekTo(sbHotVideos.getProgress());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

//        videoView.requestFocus();
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                videoView.seekTo(position);
//                if (position == 0) {
//                    videoView.start();
//                }
//                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int width, int height) {
//                        mediaController.setAnchorView(videoView);
//                    }
//                });
//            }
//        });

    }

    private void createVideoView() {
        String path = hotVideos.getFile_mp4();
        videoView.setVideoURI(Uri.parse(path));
        tvTitleHotVideos.setText(hotVideos.getTitle());
    }

    private void setDuration() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvDuration.setText(simpleDateFormat.format(videoView.getDuration()));
        sbHotVideos.setMax(videoView.getDuration());

    }

    private void updateTimeOnSeekBar() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                tvStart.setText(simpleDateFormat.format(videoView.getCurrentPosition()));
                sbHotVideos.setProgress(videoView.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        }, 500);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //lưu STATE_COUNTER vào bundle
        outState.putInt(STATE_COUNTER, counter);

    }

}
