/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.tienda;


import java.util.Objects;

/**
 *
 * @author Juan José Henao y Sebastian Arias
 */
public class Compra {

    private String fecha;
    private String nombreTienda;
    private String codigoFactura;
    private String nitTienda;

    public Compra() {
    }

    public Compra(String fecha, String nombreTienda, String codigoFactura, String nitTienda) {
        this.fecha = fecha;
        this.nombreTienda = nombreTienda;
        this.codigoFactura = codigoFactura;
        this.nitTienda = nitTienda;
    }

    public Compra(String fecha, String nombreTienda, String nombreTienda0, String codigoFactura, String nitTienda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getNitTienda() {
        return nitTienda;
    }

    public void setNitTienda(String nitTienda) {
        this.nitTienda = nitTienda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.fecha);
        hash = 97 * hash + Objects.hashCode(this.nombreTienda);
        hash = 97 * hash + Objects.hashCode(this.codigoFactura);
        hash = 97 * hash + Objects.hashCode(this.nitTienda);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compra other = (Compra) obj;
        if (!Objects.equals(this.nombreTienda, other.nombreTienda)) {
            return false;
        }
        if (!Objects.equals(this.codigoFactura, other.codigoFactura)) {
            return false;
        }
        if (!Objects.equals(this.nitTienda, other.nitTienda)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Compra{" + "fecha=" + fecha + ", nombreTienda=" + nombreTienda + ", codigoFactura=" + codigoFactura + ", nitTienda=" + nitTienda + '}';
    }

}
