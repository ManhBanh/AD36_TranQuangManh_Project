package vn.edu.devpro.watchvideo.Categories;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import vn.edu.devpro.watchvideo.Define;
import vn.edu.devpro.watchvideo.R;

public class CategoriesFragment extends Fragment {
    private static final String TAG = "CategoriesFragment";
    ArrayList<Categories> categoriesArrayList;
    CategoriesAdapter categoriesAdapter;
    RecyclerView rvCategories;

    public static CategoriesFragment newInstance() {

        Bundle args = new Bundle();

        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        new GetProduct(Define.get_Categories_URL).execute();
        rvCategories = view.findViewById(R.id.rvCategories);
        categoriesArrayList = new ArrayList<>();
        return view;
    }

    class GetProduct extends AsyncTask<Void, Void, Void> {
        String newURL, result = "";

        public GetProduct(String newURL) {
            this.newURL = newURL;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(newURL);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                int byteCharacter;
                while ((byteCharacter = inputStream.read()) != -1) {
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
                JSONArray jsonArray = new JSONArray(result);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String thumb = jsonObject.getString("thumb");
                    String title = jsonObject.getString("title");
                    categoriesArrayList.add(new Categories(id, title, thumb));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            categoriesAdapter = new CategoriesAdapter(categoriesArrayList, getContext());
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
            rvCategories.setLayoutManager(layoutManager);
            rvCategories.setAdapter(categoriesAdapter);
        }
    }
}
