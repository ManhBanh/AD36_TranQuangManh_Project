package vn.edu.devpro.watchvideo.ItemCategory;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import vn.edu.devpro.watchvideo.R;

public class WatchItemCategoryActivity extends AppCompatActivity {

    ItemCategory itemCategory;
    ArrayList<ItemCategory> itemCategoryArrayList;

    VideoView vvWatchItemCategory;
    Toolbar tbWatchItemCategory;
    TextView tvItemCategoryTitle, tvStart1, tvDuration1;
    SeekBar sbWatchItemCategory;

    RelativeLayout rlTong, rlVideoView, rlTimeController1, rlController1;
    ImageView imgScreenOrientation, imgPlay1, imgReplay10_1, imgForward10_1;

    RecyclerView rvWatchItemCategory;
    ItemCategoryAdapter itemCategoryAdapter;

    boolean check = true, orientationCheck = false;
    Handler handler;
    Runnable runnable;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_item_category);
        this.setTitle(null);

        rlTong = findViewById(R.id.rlTong);

        //Danh sách video liên quan
        rvWatchItemCategory = findViewById(R.id.rvWatchItemCategory);

        //Bộ phát video
        rlVideoView = findViewById(R.id.rlVideoView);
        rlController1 = findViewById(R.id.rlController1);
        rlTimeController1 = findViewById(R.id.rlTimeController1);
        tbWatchItemCategory = findViewById(R.id.tbWatchItemCategory);
        vvWatchItemCategory = findViewById(R.id.vvWatchItemCategory);
        tvItemCategoryTitle = findViewById(R.id.tvItemCategoryTitle);
        imgPlay1 = findViewById(R.id.imgPlay1);
        imgReplay10_1 = findViewById(R.id.imgReplay10_1);
        imgForward10_1 = findViewById(R.id.imgForward10_1);
        tvStart1 = findViewById(R.id.tvStart1);
        tvDuration1 = findViewById(R.id.tvDuration1);
        sbWatchItemCategory = findViewById(R.id.sbWatchItemCategory);
        imgScreenOrientation = findViewById(R.id.imgScreenOrientation);

        itemCategory = (ItemCategory) getIntent().getSerializableExtra("videoitemcategory");
        itemCategoryArrayList = (ArrayList<ItemCategory>) getIntent().getSerializableExtra("itemCategoryArrayList");

        //ẩn ActionBar và thêm thanh ToolBar
        setSupportActionBar(tbWatchItemCategory);

        //Khởi tạo video và bắt đầu chạy
        createVideoView();
        vvWatchItemCategory.start();
        updateTimeOnSeekBar();
        //

        if (check == true) {
            hideVideoControllerAndToolBar();
            check = false;
        }

        vvWatchItemCategory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (check == true) {
                    rlController1.setVisibility(View.GONE);
                    rlTimeController1.setVisibility(View.GONE);
                    tbWatchItemCategory.setVisibility(View.GONE);
                    check = false;
                } else {
                    rlController1.setVisibility(View.VISIBLE);
                    rlTimeController1.setVisibility(View.VISIBLE);
                    tbWatchItemCategory.setVisibility(View.VISIBLE);
                    check = true;
                }
                if (rlController1.getVisibility() == View.VISIBLE) {
                    hideVideoControllerAndToolBar();
                    check = false;
                }
                return false;
            }
        });

        imgPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vvWatchItemCategory.isPlaying()) {
                    vvWatchItemCategory.pause();
                    imgPlay1.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                } else {
                    vvWatchItemCategory.start();
                    imgPlay1.setImageResource(R.drawable.ic_pause_white_24dp);
                }
                updateTimeOnSeekBar();
            }
        });

        //Cập nhật thời gian trên thanh SeekBar sbWatchItemCategory
        sbWatchItemCategory.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                vvWatchItemCategory.seekTo(sbWatchItemCategory.getProgress());
            }
        });

        /**
         * Tua thời gian
         */
        imgReplay10_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sbWatchItemCategory.setProgress(vvWatchItemCategory.getCurrentPosition() - 10000);
                vvWatchItemCategory.seekTo(sbWatchItemCategory.getProgress());
                updateTimeOnSeekBar();
            }
        });

        imgForward10_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sbWatchItemCategory.setProgress(vvWatchItemCategory.getCurrentPosition() + 10000);
                vvWatchItemCategory.seekTo(sbWatchItemCategory.getProgress());
                updateTimeOnSeekBar();
            }
        });

        //Xoay ngang màn hình
        imgScreenOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orientationCheck == false) {
                    RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) rlVideoView.getLayoutParams();
                    params1.width = params1.MATCH_PARENT;
                    params1.height = params1.MATCH_PARENT;
                    rlVideoView.setLayoutParams(params1);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    imgScreenOrientation.setImageResource(R.drawable.ic_fullscreen_exit_white_24dp);
                    orientationCheck = true;
                } else {
                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) rlVideoView.getLayoutParams();
                    params2.width = params2.MATCH_PARENT;
                    params2.height = (int) 400;
                    rlVideoView.setLayoutParams(params2);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    imgScreenOrientation.setImageResource(R.drawable.ic_fullscreen_white_24dp);
                    orientationCheck = false;
                }
            }
        });
        //

        //Danh sách video liên quan
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 1, RecyclerView.VERTICAL, false);
        itemCategoryAdapter = new ItemCategoryAdapter(itemCategoryArrayList, getBaseContext());
        rvWatchItemCategory.setLayoutManager(layoutManager);
        rvWatchItemCategory.setAdapter(itemCategoryAdapter);

        itemCategoryAdapter.setiOnClickItemCategory(new IOnClickItemCategory() {
            @Override
            public void onClick(ItemCategory itemCategory1) {
                String path = itemCategory1.getFile_mp4();
                vvWatchItemCategory.setVideoURI(Uri.parse(path));
                tvItemCategoryTitle.setText(itemCategory1.getTitle());
                vvWatchItemCategory.start();
                updateTimeOnSeekBar();
            }
        });
    }

    private void createVideoView() {
        String path = itemCategory.getFile_mp4();
        vvWatchItemCategory.setVideoURI(Uri.parse(path));
        tvItemCategoryTitle.setText(itemCategory.getTitle());
    }

    private void setDuration() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvDuration1.setText(simpleDateFormat.format(vvWatchItemCategory.getDuration()));
        sbWatchItemCategory.setMax(vvWatchItemCategory.getDuration());
    }

    /**
     * Cập nhật thời gian trên SeekBar sbWatchItemCategory
     */
    private void updateTimeOnSeekBar() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (vvWatchItemCategory != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvStart1.setText(simpleDateFormat.format(vvWatchItemCategory.getCurrentPosition()));
                    sbWatchItemCategory.setProgress(vvWatchItemCategory.getCurrentPosition());
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
                rlController1.setVisibility(View.GONE);
                rlTimeController1.setVisibility(View.GONE);
                tbWatchItemCategory.setVisibility(View.GONE);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 3000, 1000);
    }
}
