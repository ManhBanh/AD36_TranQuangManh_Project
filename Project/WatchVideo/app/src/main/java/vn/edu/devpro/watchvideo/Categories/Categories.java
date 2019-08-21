package vn.edu.devpro.watchvideo.Categories;

import java.io.Serializable;

public class Categories implements Serializable {
    private String id, title, thumb;
//    private ArrayList<ItemCategory> itemCategoryArrayList;

    public Categories() {
    }

    public Categories(String id, String title, String thumb) {
        this.id = id;
        this.title = title;
        this.thumb = thumb;
//        this.itemCategoryArrayList = itemCategoryArrayList;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumb() {
        return thumb;
    }

//    public ArrayList<ItemCategory> getItemCategoryArrayList() {
//        return itemCategoryArrayList;
//    }
}
