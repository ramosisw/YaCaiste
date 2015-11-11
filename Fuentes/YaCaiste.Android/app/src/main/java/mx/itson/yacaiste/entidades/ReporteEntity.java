package mx.itson.yacaiste.entidades;

import java.util.Date;
import java.util.List;

import mx.itson.yacaiste.enumeradores.GravedadBache;

/**
 * @author jcramos
 * ramos.isw@gmail.com
 * Created by jcramos on 10/11/2015.
 */
public class ReporteEntity {
    private int id;
    private Date fecha;
    private UsuarioEntity usuario;
    private GravedadBache gravedad;
    private List<FotoEntity> fotografias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public GravedadBache getGravedad() {
        return gravedad;
    }

    public void setGravedad(GravedadBache gravedad) {
        this.gravedad = gravedad;
    }

    public List<FotoEntity> getFotografias() {
        return fotografias;
    }

    public void setFotografias(List<FotoEntity> fotografias) {
        this.fotografias = fotografias;
    }
}
