package vn.edu.devpro.watchvideo.ItemCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

import vn.edu.devpro.watchvideo.R;

public class WatchItemCategory extends AppCompatActivity {
    VideoView vvWatchItemCategory;
    MediaController mediaController;

    Toolbar tbWatchItemCategory;
    TextView tvItemCategoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_item_category);
        this.setTitle(null);

        tbWatchItemCategory = findViewById(R.id.tbWatchItemCategory);
        tvItemCategoryTitle = findViewById(R.id.tvItemCategoryTitle);

        vvWatchItemCategory = findViewById(R.id.vvWatchItemCategory);

        ItemCategory itemCategory = (ItemCategory) getIntent().getSerializableExtra("videoitemcategory");
        ArrayList<ItemCategory> itemCategoryArrayList = (ArrayList<ItemCategory>) getIntent().getSerializableExtra("itemCategoryArrayList");

        setSupportActionBar(tbWatchItemCategory);
        tvItemCategoryTitle.setText(String.valueOf(itemCategoryArrayList.size()));
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
