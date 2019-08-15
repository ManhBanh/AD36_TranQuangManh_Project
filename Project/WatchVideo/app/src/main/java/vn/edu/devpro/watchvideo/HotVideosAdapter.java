package vn.edu.devpro.watchvideo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotVideosAdapter extends RecyclerView.Adapter<HotVideosAdapter.Viewholder> {
    ArrayList<HotVideos> hotVideosArrayList;
    IOnClickHotVideos iOnClickHotVideos;

    public HotVideosAdapter(ArrayList<HotVideos> hotVideosArrayList) {
        this.hotVideosArrayList = hotVideosArrayList;
    }

    public void setiOnClickHotVideos(IOnClickHotVideos iOnClickHotVideos) {
        this.iOnClickHotVideos = iOnClickHotVideos;
    }

    @NonNull
    @Override
    public HotVideosAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_hot_videos, parent, false);
        Viewholder vh = new Viewholder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HotVideosAdapter.Viewholder holder, int position) {
        final HotVideos hotVideos = hotVideosArrayList.get(position);

        holder.imgAvatar.setImageResource(R.drawable.ic_launcher_background);
        holder.tvTitle.setText(hotVideos.getTitle());
        holder.tvAritst_Name.setText(hotVideos.getArtist_name());
        holder.tvDate_Published.setText(hotVideos.getDate_published());

    }

    @Override
    public int getItemCount() {
        return hotVideosArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvTitle, tvAritst_Name, tvDate_Published;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAritst_Name = itemView.findViewById(R.id.tvArtist_Name);
            tvDate_Published = itemView.findViewById(R.id.tvDate_Published);
        }
    }
}
