package vn.edu.devpro.watchvideo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import vn.edu.devpro.watchvideo.Categories.CategoriesFragment;
import vn.edu.devpro.watchvideo.HotVideos.HotVideos;
import vn.edu.devpro.watchvideo.HotVideos.HotVideosFragment;
import vn.edu.devpro.watchvideo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements HotVideosFragment.IOnClickObject{
    ActivityMainBinding activityMainBinding;
    ActionBarDrawerToggle toggle;
    public static String jSon = "";
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

    public void getFragment(Fragment fragment){
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "getFragment: "+ e.getMessage());
        }
    }

    @Override
    public void onClickObject(HotVideos hotVideos) {
        Intent intent = new Intent(getBaseContext(), WatchActivity.class);
        intent.putExtra("hotvideos", hotVideos);
        startActivity(intent);
    }

    class getProduct extends AsyncTask<Void, Void, Void>{
        String urlNew, result = "";

        public getProduct(String urlNew) {
            this.urlNew = urlNew;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(urlNew);
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                int byteCharacter;
                while((byteCharacter = inputStream.read()) != -1){
                    result += (char)byteCharacter;
                }
                Log.d(TAG, "doInBackground: "+ result);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            jSon = result;
        }
    }
}
