package vn.edu.devpro.watchvideo.Categories;

public class Categories {
    private String id, title, thumb;

    public Categories() {
    }

    public Categories(String id, String title, String thumb) {
        this.id = id;
        this.title = title;
        this.thumb = thumb;
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
}
