package vn.edu.devpro.watchvideo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HotVideosFragment extends Fragment {
    private static final String TAG = "HotVideosFragment";
    String json = "";
    RecyclerView recyclerView;      TextView test;
    HotVideosAdapter hotVideosAdapter;
    ArrayList<HotVideos> hotVideosArrayList;

    public static HotVideosFragment newInstance() {

        Bundle args = new Bundle();

        HotVideosFragment fragment = new HotVideosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_videos, container, false);
        new GetProduct("https://demo5639557.mockable.io/getVideoHot").execute();
        recyclerView = view.findViewById(R.id.rvHotVideos);     test = view.findViewById(R.id.test);
        hotVideosArrayList = new ArrayList<>();


        return view;
    }
    class GetProduct extends AsyncTask<Void, Void, Void> {
        String urlNew, result = "";

        public GetProduct(String urlNew) {
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
            json = result;
            try {
                JSONArray jsonA_Hot_Videos = new JSONArray(json);
                for(int i = 0; i < jsonA_Hot_Videos.length(); i++){
                    JSONObject jsonO_Hot_Videos = jsonA_Hot_Videos.getJSONObject(i);
                    String id = jsonO_Hot_Videos.getString("id");
                    String title = jsonO_Hot_Videos.getString("title");
                    String avatar = jsonO_Hot_Videos.getString("avatar");
                    String name = jsonO_Hot_Videos.getString("artist_name");
                    String date = jsonO_Hot_Videos.getString("date_published");
                    hotVideosArrayList.add(new HotVideos(id, title, avatar, name, date));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
//        hotVideosArrayList.add(new HotVideos("1", "asdf", "asfd", "fsad", "asdf"));
            hotVideosAdapter = new HotVideosAdapter(hotVideosArrayList);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(hotVideosAdapter);
//            test.setText(json);
        }
    }

}
