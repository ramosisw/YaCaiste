package mx.itson.yacaiste.modelos;

import java.util.Date;
import java.util.List;

import mx.itson.yacaiste.entidades.FotoEntity;

/**
 * Created by Admin on 23/06/2015.
 */
public class ReportEntity {
    private int id;
    private String direccion;
    private Date fecha;
    private double rank;
    private int numReportes;
    private List<FotoEntity> fotografias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public int getNumReportes() {
        return numReportes;
    }

    public void setNumReportes(int numReportes) {
        this.numReportes = numReportes;
    }

    public List<FotoEntity> getFotografias() {
        return fotografias;
    }

    public void setFotografias(List<FotoEntity> fotografias) {
        this.fotografias = fotografias;
    }
}
