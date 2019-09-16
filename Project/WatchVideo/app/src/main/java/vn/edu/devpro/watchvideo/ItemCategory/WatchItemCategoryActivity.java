package vn.edu.devpro.watchvideo.ItemCategory;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import vn.edu.devpro.watchvideo.R;

public class WatchItemCategoryActivity extends AppCompatActivity {
    VideoView vvWatchItemCategory;
    MediaController mediaController;

    Toolbar tbWatchItemCategory;
    TextView tvItemCategoryTitle;


    RelativeLayout rlTong;
    RelativeLayout rlVideoView;
    ImageView imgScreenOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_item_category);
        this.setTitle(null);

        rlTong = findViewById(R.id.rlTong);
        rlVideoView = findViewById(R.id.rlVideoView);
        imgScreenOrientation = findViewById(R.id.imgScreenOrientation);

        tbWatchItemCategory = findViewById(R.id.tbWatchItemCategory);
        tvItemCategoryTitle = findViewById(R.id.tvItemCategoryTitle);

        vvWatchItemCategory = findViewById(R.id.vvWatchItemCategory);

        ItemCategory itemCategory = (ItemCategory) getIntent().getSerializableExtra("videoitemcategory");
        ArrayList<ItemCategory> itemCategoryArrayList = (ArrayList<ItemCategory>) getIntent().getSerializableExtra("itemCategoryArrayList");

        setSupportActionBar(tbWatchItemCategory);
        tvItemCategoryTitle.setText(itemCategory.getTitle());
//        if(mediaController == null){
//            mediaController = new MediaController(this);
//            vvWatchItemCategory.setMediaController(mediaController);
//            mediaController.setAnchorView(vvWatchItemCategory);
//        }

        try{
            String path = itemCategory.getFile_mp4();
            vvWatchItemCategory.setVideoURI(Uri.parse(path));
            vvWatchItemCategory.start();
        }catch (Exception e){
            e.printStackTrace();
        }

        imgScreenOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlVideoView.getLayoutParams();
                params.width = params.MATCH_PARENT;
                params.height = params.MATCH_PARENT;
                rlVideoView.setLayoutParams(params);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
    }
}
