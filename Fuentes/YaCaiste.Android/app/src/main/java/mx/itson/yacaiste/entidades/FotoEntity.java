package mx.itson.yacaiste.entidades;

/**
 * @author jcramos
 * ramos.isw@gmail.com
 * Created by jcramos on 10/11/2015.
 */
public class FotoEntity {
    private int id;
    private String path_local;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath_local() {
        return path_local;
    }

    public void setPath_local(String path_local) {
        this.path_local = path_local;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
