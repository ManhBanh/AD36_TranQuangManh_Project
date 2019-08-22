package vn.edu.devpro.watchvideo.ItemCategory;

import java.io.Serializable;

public class ItemCategory implements Serializable {
    private String id, title, avatar, file_mp4, date_published;

    public ItemCategory() {
    }

    public ItemCategory(String id, String title, String avatar, String file_mp4, String date_published) {
        this.id = id;
        this.title = title;
        this.avatar = avatar;
        this.file_mp4 = file_mp4;
        this.date_published = date_published;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFile_mp4() {
        return file_mp4;
    }

    public String getDate_published() {
        return date_published;
    }
}
