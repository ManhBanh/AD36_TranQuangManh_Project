package vn.edu.devpro.watchvideo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Viewholder> {

    ArrayList<Categories> categoriesArrayList;
    Context context;
    IOnClickCategories iOnClickCategories;

    public CategoriesAdapter(ArrayList<Categories> categoriesArrayList, Context context) {
        this.categoriesArrayList = categoriesArrayList;
        this.context = context;
    }

    public void setiOnClickCategories(IOnClickCategories iOnClickCategories) {
        this.iOnClickCategories = iOnClickCategories;
    }

    @NonNull
    @Override
    public CategoriesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_categories, parent, false);
        Viewholder vh = new Viewholder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.Viewholder holder, int position) {
        final Categories categories = categoriesArrayList.get(position);
        Glide.with(context)
                .load(categories.getThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgThumb);
        holder.tvTitle.setText(categories.getTitle());
    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        TextView tvTitle;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imgThumb = itemView.findViewById(R.id.imgThumb);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
