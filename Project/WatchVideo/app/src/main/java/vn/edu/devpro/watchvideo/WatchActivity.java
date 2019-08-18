package vn.edu.devpro.watchvideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class WatchActivity extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;
    HotVideos hotVideos;
    int position;

    Toolbar tbWatchHotVideos;
    Button btnBack;
    TextView tvTitleHotVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);

        tbWatchHotVideos = findViewById(R.id.tbWatchHotVideos);
        btnBack = findViewById(R.id.btnBack);
        tvTitleHotVideos = findViewById(R.id.tvTitleHotVideos);

        setSupportActionBar(tbWatchHotVideos);

        hotVideos = (HotVideos) getIntent().getSerializableExtra("hotvideos");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitleHotVideos.setText(hotVideos.getTitle());

        videoView = findViewById(R.id.vvWatch);
        // Tạo bộ điều khiển
        if (mediaController == null) {
            mediaController = new MediaController(WatchActivity.this);

            // Neo vị trí của MediaController với VideoView.
            mediaController.setAnchorView(videoView);

            // Sét đặt bộ điều khiển cho VideoView.
            videoView.setMediaController(mediaController);
        }

        try {
            String path = hotVideos.getFile_mp4();
            videoView.setVideoURI(Uri.parse(path));
            videoView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();
                }
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int width, int height) {
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

    }

    // Tìm ID ứng với tên của file nguồn (Trong thư mục raw).
//    public int getRawResIdByName(String resName) {
//        String pkgName = this.getPackageName();
//
//        // Trả về 0 nếu không tìm thấy.
//        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
//        Log.i("AndroidVideoView", "Res Name: " + resName + "==> Res ID = " + resID);
//        return resID;
//    }
}
