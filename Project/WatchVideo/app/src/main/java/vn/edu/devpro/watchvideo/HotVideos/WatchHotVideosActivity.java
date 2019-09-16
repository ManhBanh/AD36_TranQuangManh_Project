package vn.edu.devpro.watchvideo.HotVideos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import vn.edu.devpro.watchvideo.MainActivity;
import vn.edu.devpro.watchvideo.R;

public class WatchHotVideosActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
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
    Boolean check = true;

    RelativeLayout rlController;
    Handler handler;
    Timer timer;
    Runnable runnable;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_hot_videos);

        gestureDetector = new GestureDetector(WatchHotVideosActivity.this, WatchHotVideosActivity.this);

        if (savedInstanceState != null) {
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
        rlController = findViewById(R.id.rlController);

        setSupportActionBar(tbWatchHotVideos);

        hotVideos = (HotVideos) getIntent().getSerializableExtra("hotvideos");

        createVideoView();
        videoView.start();
        updateTimeOnSeekBar();

        if (check == true) {
            hideVideoControllerAndToolBar();
            check = false;
        }

//        videoView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (check == true) {
//                    rlController.setVisibility(View.GONE);
//                    tbWatchHotVideos.setVisibility(View.GONE);
//                    check = false;
//                } else {
//                    rlController.setVisibility(View.VISIBLE);
//                    tbWatchHotVideos.setVisibility(View.VISIBLE);
//                    check = true;
//                }
//                if (rlController.getVisibility() == View.VISIBLE) {
//                    hideVideoControllerAndToolBar();
//                    check = false;
//                }
//                return false;
//            }
//

//        });


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
//                setDuration();
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
                if (videoView != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvStart.setText(simpleDateFormat.format(videoView.getCurrentPosition()));
                    sbHotVideos.setProgress(videoView.getCurrentPosition());
                    setDuration();
                    handler.postDelayed(this, 500);
                }
            }
        }, 500);
    }

    private void hideVideoControllerAndToolBar() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                timer.cancel();
                rlController.setVisibility(View.GONE);
                tbWatchHotVideos.setVisibility(View.GONE);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 3000, 9000);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //lưu STATE_COUNTER vào bundle
        outState.putInt(STATE_COUNTER, counter);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (check == true) {
            rlController.setVisibility(View.GONE);
            tbWatchHotVideos.setVisibility(View.GONE);
            check = false;
        } else {
            rlController.setVisibility(View.VISIBLE);
            tbWatchHotVideos.setVisibility(View.VISIBLE);
            check = true;
        }
        if (rlController.getVisibility() == View.VISIBLE) {
            hideVideoControllerAndToolBar();
            check = false;
        }
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (videoView.isPlaying()) {
            videoView.pause();
            imgPlay.setImageResource(R.drawable.ic_play_arrow_white_24dp);
        } else {
            videoView.start();
            imgPlay.setImageResource(R.drawable.ic_pause_white_24dp);
        }
//                setDuration();
        updateTimeOnSeekBar();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
