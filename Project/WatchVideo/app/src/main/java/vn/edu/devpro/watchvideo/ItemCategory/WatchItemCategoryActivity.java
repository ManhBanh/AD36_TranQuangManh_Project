package vn.edu.devpro.watchvideo.ItemCategory;

import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.MediaController;
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

    LinearLayout llTong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_item_category);
        this.setTitle(null);
        llTong = findViewById(R.id.llTong);

        tbWatchItemCategory = findViewById(R.id.tbWatchItemCategory);
        tvItemCategoryTitle = findViewById(R.id.tvItemCategoryTitle);

        vvWatchItemCategory = findViewById(R.id.vvWatchItemCategory);

        ItemCategory itemCategory = (ItemCategory) getIntent().getSerializableExtra("videoitemcategory");
        ArrayList<ItemCategory> itemCategoryArrayList = (ArrayList<ItemCategory>) getIntent().getSerializableExtra("itemCategoryArrayList");

        setSupportActionBar(tbWatchItemCategory);
        tvItemCategoryTitle.setText(llTong.getHeight() + " " + llTong.getWidth());
        if(mediaController == null){
            mediaController = new MediaController(this);
            vvWatchItemCategory.setMediaController(mediaController);
            mediaController.setAnchorView(vvWatchItemCategory);
        }

        try{
            String path = itemCategory.getFile_mp4();
            vvWatchItemCategory.setVideoURI(Uri.parse(path));
            vvWatchItemCategory.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
