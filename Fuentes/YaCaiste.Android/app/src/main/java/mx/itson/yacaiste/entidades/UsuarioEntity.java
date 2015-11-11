package mx.itson.yacaiste.entidades;

/**
 * @author jcramos
 * ramos.isw@gmail.com
 * Created by jcramos on 10/11/2015.
 */
public class UsuarioEntity {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
