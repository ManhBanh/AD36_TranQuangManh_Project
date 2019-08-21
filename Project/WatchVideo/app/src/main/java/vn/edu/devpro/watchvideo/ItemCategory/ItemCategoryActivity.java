package vn.edu.devpro.watchvideo.ItemCategory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import vn.edu.devpro.watchvideo.Categories.Categories;
import vn.edu.devpro.watchvideo.Define;
import vn.edu.devpro.watchvideo.R;

public class ItemCategoryActivity extends AppCompatActivity {
    private static final String TAG = "ItemCategoryActivity";
    Toolbar tbCategory;
    Button btnBack;
    TextView tvCategoryTitle;
    RecyclerView rvItemCategory;
    ItemCategoryAdapter itemCategoryAdapter;
    Categories categories;

    ArrayList<ItemCategory> itemCategoryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category);

        tbCategory = findViewById(R.id.tbCategory);
        setSupportActionBar(tbCategory);

        btnBack = findViewById(R.id.btnBackFromItemCategoryActivity);
        tvCategoryTitle = findViewById(R.id.tvCategoryTitle);
        rvItemCategory = findViewById(R.id.rvItemCategory);

        itemCategoryArrayList = new ArrayList<>();

        categories = (Categories) getIntent().getSerializableExtra("itemcategory");
        tvCategoryTitle.setText(categories.getTitle());

        new GetItemCategory(Define.Get_Item_Category_URL).execute();
    }

    class GetItemCategory extends AsyncTask<Void, Void, Void> {
        String newURL, result = "";

        public GetItemCategory(String newURL) {
            this.newURL = newURL;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(newURL);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                int byteCharacter;
                while ((byteCharacter = inputStream.read()) != 1) {
                    result += (char) byteCharacter;
                }
                Log.d(TAG, "doInBackground: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                JSONArray jsonA_ItemCategory = new JSONArray(result);
                for (int i = 0; i < jsonA_ItemCategory.length(); i++) {
                    JSONObject jsonO_ItemCategory = jsonA_ItemCategory.getJSONObject(i);
                    String id = jsonO_ItemCategory.getString("id");
                    String avatar = jsonO_ItemCategory.getString("avatar");
                    String title = jsonO_ItemCategory.getString("title");
                    String file_mp4 = jsonO_ItemCategory.getString("file_mp4");
                    String date_published = jsonO_ItemCategory.getString("date_published");
                    itemCategoryArrayList.add(new ItemCategory(id, title, avatar, file_mp4, date_published));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 1, RecyclerView.VERTICAL, false);
            itemCategoryAdapter = new ItemCategoryAdapter(itemCategoryArrayList, getBaseContext());
            rvItemCategory.setLayoutManager(layoutManager);
            rvItemCategory.setAdapter(itemCategoryAdapter);
        }
    }
}
