package vn.edu.devpro.watchvideo.HotVideos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import vn.edu.devpro.watchvideo.R;

public class HotVideosAdapter extends RecyclerView.Adapter<HotVideosAdapter.Viewholder> {
    ArrayList<HotVideos> hotVideosArrayList;
    Context context;
    IOnClickHotVideos iOnClickHotVideos;

    public HotVideosAdapter(ArrayList<HotVideos> hotVideosArrayList, Context context) {
        this.hotVideosArrayList = hotVideosArrayList;
        this.context = context;
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

        Glide.with(context)
                .load(hotVideos.getAvatar())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgAvatar);
        holder.tvTitle.setText(hotVideos.getTitle());
        holder.tvAritst_Name.setText("Feliks");
        holder.tvDate_Published.setText(hotVideos.getDate_published());

        holder.llHotVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickHotVideos.onClick(hotVideos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotVideosArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvTitle, tvAritst_Name, tvDate_Published;
        LinearLayout llHotVideos;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAritst_Name = itemView.findViewById(R.id.tvArtist_Name);
            tvDate_Published = itemView.findViewById(R.id.tvDate_Published);
            llHotVideos = itemView.findViewById(R.id.llHotVideos);
        }
    }
}
