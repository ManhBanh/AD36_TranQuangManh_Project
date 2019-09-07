package vn.edu.devpro.watchvideo.HotVideos;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import vn.edu.devpro.watchvideo.Define;
import vn.edu.devpro.watchvideo.R;

public class HotVideosFragment extends Fragment {
    private static final String TAG = "HotVideosFragment";

    ProgressBar pbLoadingHotVideos;

    RecyclerView recyclerView;
    HotVideosAdapter hotVideosAdapter;
    ArrayList<HotVideos> hotVideosArrayList;

    IOnClickObject iOnClickObject;

    public interface IOnClickObject {
        public void onClickObject(HotVideos hotVideos);
    }

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
        new GetProduct(Define.HOT_VIDEO_URL).execute();
        recyclerView = view.findViewById(R.id.rvHotVideos);
        pbLoadingHotVideos = view.findViewById(R.id.pbLoadingHotVideos);
        hotVideosArrayList = new ArrayList<>();
        pbLoadingHotVideos.setVisibility(View.VISIBLE);
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
            pbLoadingHotVideos.setVisibility(View.GONE);
            try {
                JSONArray jsonA_Hot_Videos = new JSONArray(result);
                for (int i = 0; i < jsonA_Hot_Videos.length(); i++) {
                    JSONObject jsonO_Hot_Videos = jsonA_Hot_Videos.getJSONObject(i);
                    String id = jsonO_Hot_Videos.getString("id");
                    String title = jsonO_Hot_Videos.getString("title");
                    String avatar = jsonO_Hot_Videos.getString("avatar");
                    String name = jsonO_Hot_Videos.getString("artist_name");
                    String date = jsonO_Hot_Videos.getString("date_published");
                    String path = jsonO_Hot_Videos.getString("file_mp4");
                    hotVideosArrayList.add(new HotVideos(id, title, avatar, name, date, path));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            hotVideosAdapter = new HotVideosAdapter(hotVideosArrayList, getContext());
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),
                    2, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(hotVideosAdapter);

            hotVideosAdapter.setiOnClickHotVideos(new IOnClickHotVideos() {
                @Override
                public void onClick(HotVideos hotVideos) {
                    iOnClickObject.onClickObject(hotVideos);
                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnClickObject) {
            iOnClickObject = (IOnClickObject) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iOnClickObject = null;
    }

}
