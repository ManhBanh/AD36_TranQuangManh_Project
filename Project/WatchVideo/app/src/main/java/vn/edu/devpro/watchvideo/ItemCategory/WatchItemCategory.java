package vn.edu.devpro.watchvideo.ItemCategory;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import vn.edu.devpro.watchvideo.R;

public class WatchItemCategory extends AppCompatActivity {
    VideoView vvWatchItemCategory;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_item_category);

        vvWatchItemCategory = findViewById(R.id.vvWatchItemCategory);

        ItemCategory itemCategory = (ItemCategory)getIntent().getSerializableExtra("videoitemcategory");

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
