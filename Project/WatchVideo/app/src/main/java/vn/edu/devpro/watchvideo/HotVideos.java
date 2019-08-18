package vn.edu.devpro.watchvideo;

import java.io.Serializable;

public class HotVideos implements Serializable {
    private String id;
    private String title, avatar;
    private String artist_name, date_published, file_mp4;

    public HotVideos() {
    }

    public HotVideos(String id, String title, String avatar, String artist_name, String date_published, String file_mp4) {
        this.id = id;
        this.title = title;
        this.avatar = avatar;
        this.artist_name = artist_name;
        this.date_published = date_published;
        this.file_mp4 = file_mp4;
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

    public String getArtist_name() {
        return artist_name;
    }

    public String getDate_published() {
        return date_published;
    }

    public String getFile_mp4() {
        return file_mp4;
    }
}
