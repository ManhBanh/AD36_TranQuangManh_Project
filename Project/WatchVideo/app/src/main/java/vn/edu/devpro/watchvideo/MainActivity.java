package vn.edu.devpro.watchvideo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import vn.edu.devpro.watchvideo.Categories.Categories;
import vn.edu.devpro.watchvideo.Categories.CategoriesFragment;
import vn.edu.devpro.watchvideo.HotVideos.HotVideos;
import vn.edu.devpro.watchvideo.HotVideos.HotVideosFragment;
import vn.edu.devpro.watchvideo.HotVideos.WatchHotVideosActivity;
import vn.edu.devpro.watchvideo.ItemCategory.ItemCategoryActivity;
import vn.edu.devpro.watchvideo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements HotVideosFragment.IOnClickObject,
        CategoriesFragment.IOnClickItemCategory {

    ActivityMainBinding activityMainBinding;
    ActionBarDrawerToggle toggle;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(null);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(activityMainBinding.toolbar);

        toggle = new ActionBarDrawerToggle(this, activityMainBinding.drawer,
                activityMainBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        activityMainBinding.drawer.addDrawerListener(toggle);
        toggle.syncState();

        getFragment(HotVideosFragment.newInstance());
        activityMainBinding.tvHotVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragment(HotVideosFragment.newInstance());
            }
        });
        activityMainBinding.tvCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragment(CategoriesFragment.newInstance());
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (activityMainBinding.drawer.isDrawerOpen(GravityCompat.START)) {
            activityMainBinding.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getFragment: " + e.getMessage());
        }
    }

    @Override
    public void onClickObject(HotVideos hotVideos) {
        Intent intent = new Intent(getBaseContext(), WatchHotVideosActivity.class);
        intent.putExtra("hotvideos", hotVideos);
        startActivity(intent);
    }

    @Override
    public void onClickItemCategory(Categories categories) {
        Intent intent = new Intent(getBaseContext(), ItemCategoryActivity.class);
        intent.putExtra("itemcategory", categories);
        startActivity(intent);
    }
}
