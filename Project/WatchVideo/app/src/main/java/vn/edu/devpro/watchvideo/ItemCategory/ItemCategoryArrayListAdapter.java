package vn.edu.devpro.watchvideo.ItemCategory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemCategoryArrayListAdapter extends RecyclerView.Adapter<ItemCategoryArrayListAdapter.ViewHolder> {

    ArrayList<ItemCategory> itemCategoryArrayList;
    Context context;
    IOnClickItemCategory iOnClickItemCategory;

    public ItemCategoryArrayListAdapter(ArrayList<ItemCategory> itemCategoryArrayList, Context context) {
        this.itemCategoryArrayList = itemCategoryArrayList;
        this.context = context;
    }

    public void setiOnClickItemCategory(IOnClickItemCategory iOnClickItemCategory) {
        this.iOnClickItemCategory = iOnClickItemCategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
