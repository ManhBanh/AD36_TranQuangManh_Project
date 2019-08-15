package vn.edu.devpro.watchvideo;

public class HotVideos {
    String id;
    String title, avatar;
    String artist_name, date_published;

    public HotVideos() {
    }

    public HotVideos(String id, String title, String avatar, String artist_name, String date_published) {
        this.id = id;
        this.title = title;
        this.avatar = avatar;
        this.artist_name = artist_name;
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

    public String getArtist_name() {
        return artist_name;
    }

    public String getDate_published() {
        return date_published;
    }
}
