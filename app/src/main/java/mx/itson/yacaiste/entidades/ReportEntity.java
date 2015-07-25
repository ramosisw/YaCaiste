package mx.itson.yacaiste.entidades;

import java.util.Date;

/**
 * Created by Admin on 23/06/2015.
 */
public class ReportEntity {
    private int id;
    private String direccion;
    private Date fecha;
    private double rank;
    private int numReportes;

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
}
