package vn.edu.devpro.watchvideo.ItemCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import vn.edu.devpro.watchvideo.R;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.Viewholder> {

    ArrayList<ItemCategory> itemCategoryArrayList;
    Context context;
    IOnClickItemCategory iOnClickItemCategory;

    public ItemCategoryAdapter(ArrayList<ItemCategory> itemCategoryArrayList, Context context) {
        this.itemCategoryArrayList = itemCategoryArrayList;
        this.context = context;
}

    public void setiOnClickItemCategory(IOnClickItemCategory iOnClickItemCategory) {
        this.iOnClickItemCategory = iOnClickItemCategory;
    }

    @NonNull
    @Override
    public ItemCategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_itemcategory, parent, false);
        Viewholder vh = new Viewholder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCategoryAdapter.Viewholder holder, int position) {
        final ItemCategory itemCategory = itemCategoryArrayList.get(position);

        Glide.with(context)
                .load(itemCategory.getAvatar())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgItemCategory_Avatar);
        holder.tvItemCategory_Title.setText(itemCategory.getTitle());
        holder.tvItemCategory_Artist_Name.setText("Feliks");
        holder.tvItemCategory_Date_Published.setText(itemCategory.getDate_published());

        holder.llItemCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickItemCategory.onClick(itemCategory);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCategoryArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView imgItemCategory_Avatar;
        TextView tvItemCategory_Title, tvItemCategory_Artist_Name, tvItemCategory_Date_Published;
        LinearLayout llItemCategory;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imgItemCategory_Avatar = itemView.findViewById(R.id.imgItemCategory_Avatar);
            tvItemCategory_Title = itemView.findViewById(R.id.tvItemCategory_Title);
            tvItemCategory_Artist_Name = itemView.findViewById(R.id.tvItemCategory_Artist_Name);
            tvItemCategory_Date_Published = itemView.findViewById(R.id.tvItemCategory_Date_Published);
            llItemCategory = itemView.findViewById(R.id.llItemCategory);
        }
    }
}
