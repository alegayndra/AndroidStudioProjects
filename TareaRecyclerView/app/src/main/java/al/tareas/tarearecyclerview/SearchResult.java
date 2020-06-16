package al.tareas.tarearecyclerview;

import android.provider.Settings;

import java.util.Date;

public class SearchResult {
    private String _title;
    private String _description;
    private String _url;
    private String _urlToImage;
    private String _publishedDate;

    public SearchResult() {}

    public SearchResult(String title, String desc, String url, String urlImg, String publishedDate) {
        this._title = title;
        this._description = desc;
        this._url = url;
        this._urlToImage = urlImg;
        this._publishedDate = publishedDate;
    }

    // getters

    public String get_title() {
        return _title;
    }

    public String get_description() {
        return _description;
    }

    public String get_url() {
        return _url;
    }

    public String get_urlToImage() {
        return _urlToImage;
    }

    public String  get_publishedDate() {
        return _publishedDate;
    }


    // setters

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public void set_urlToImage(String _urlToImage) {
        this._urlToImage = _urlToImage;
    }

    public void set_publishedDate(String _publishedDate) {
        this._publishedDate = _publishedDate;
    }

}
