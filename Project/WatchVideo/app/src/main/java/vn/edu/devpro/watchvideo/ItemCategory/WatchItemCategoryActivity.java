package vn.edu.devpro.watchvideo.ItemCategory;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import vn.edu.devpro.watchvideo.R;

public class WatchItemCategoryActivity extends AppCompatActivity {
    VideoView vvWatchItemCategory;
    ItemCategory itemCategory;

    Toolbar tbWatchItemCategory;
    TextView tvItemCategoryTitle, tvStart1, tvDuration1;
    SeekBar sbItemCategory;

    RelativeLayout rlTong;
    RelativeLayout rlVideoView;
    ImageView imgScreenOrientation, imgPlay1;

    boolean check, orientationCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_item_category);
        this.setTitle(null);

        rlTong = findViewById(R.id.rlTong);
        rlVideoView = findViewById(R.id.rlVideoView);

        tbWatchItemCategory = findViewById(R.id.tbWatchItemCategory);
        tvItemCategoryTitle = findViewById(R.id.tvItemCategoryTitle);
        imgPlay1 = findViewById(R.id.imgPlay1);
        tvStart1 = findViewById(R.id.tvStart1);
        tvDuration1 = findViewById(R.id.tvDuration1);
        sbItemCategory = findViewById(R.id.sbItemCategory);
        imgScreenOrientation = findViewById(R.id.imgScreenOrientation);


        vvWatchItemCategory = findViewById(R.id.vvWatchItemCategory);

        itemCategory = (ItemCategory) getIntent().getSerializableExtra("videoitemcategory");
        ArrayList<ItemCategory> itemCategoryArrayList = (ArrayList<ItemCategory>) getIntent().getSerializableExtra("itemCategoryArrayList");

        setSupportActionBar(tbWatchItemCategory);

        createVideoView();
        vvWatchItemCategory.start();

        imgScreenOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orientationCheck == false) {
                    RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) rlVideoView.getLayoutParams();
                    params1.width = params1.MATCH_PARENT;
                    params1.height = params1.MATCH_PARENT;
                    rlVideoView.setLayoutParams(params1);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    imgScreenOrientation.setImageResource(R.drawable.ic_fullscreen_exit_white_24dp);
                    orientationCheck = true;
                }
                else{
                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) rlVideoView.getLayoutParams();
                    params2.width = params2.MATCH_PARENT;
                    params2.height = R.dimen._179sdp;
                    rlVideoView.setLayoutParams(params2);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    imgScreenOrientation.setImageResource(R.drawable.ic_fullscreen_white_24dp);
                    orientationCheck = false;
                }
            }
        });
    }

    private void createVideoView() {
        String path = itemCategory.getFile_mp4();
        vvWatchItemCategory.setVideoURI(Uri.parse(path));
        tvItemCategoryTitle.setText(itemCategory.getTitle());
    }
}
