package mejia.oscar.medayv5.Model;

public class ItemData {

    private String name, image, url;
    private boolean favorite;

    public ItemData(){

    }

    public ItemData(String name, String image, String url, boolean favorite) {
        this.name = name;
        this.image = image;
        this.url = url;
        this.favorite = favorite;
    }

    public boolean getFavorite(){return  favorite;}

    public void setFavorite(Boolean name){this.favorite = favorite;}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl(){return url;}

    public void setUrl(String url){this.url = url;}
}
