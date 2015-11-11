package mx.itson.yacaiste.entidades;

import java.util.Date;
import java.util.List;

import mx.itson.yacaiste.enumeradores.StatusBache;

/**
 * @author jcramos
 * ramos.isw@gmail.com
 * Created by jcramos on 10/11/2015.
 */
public class BacheEntity {
    private int id;
    private double latitud;
    private double longitud;
    private String direccion;
    private StatusBache status;
    private Date fecha;
    private List<ReporteEntity> reportes;
    private List<FotoEntity> fotografias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public StatusBache getStatus() {
        return status;
    }

    public void setStatus(StatusBache status) {
        this.status = status;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<ReporteEntity> getReportes() {
        return reportes;
    }

    public void setReportes(List<ReporteEntity> reportes) {
        this.reportes = reportes;
    }

    public List<FotoEntity> getFotografias() {
        return fotografias;
    }

    public void setFotografias(List<FotoEntity> fotografias) {
        this.fotografias = fotografias;
    }
}
