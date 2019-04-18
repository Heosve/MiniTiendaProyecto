/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.tienda;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Acer
 */
public class Venta {

    private Date fecha;
    private String nombreTienda;
    private String codigoFactura;
    private String nitTienda;

    public Venta() {
    }

    public Venta(Date fecha, String nombreTienda, String codigoFactura, String nitTienda) {
        this.fecha = fecha;
        this.nombreTienda = nombreTienda;
        this.codigoFactura = codigoFactura;
        this.nitTienda = nitTienda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.fecha);
        hash = 23 * hash + Objects.hashCode(this.nombreTienda);
        hash = 23 * hash + Objects.hashCode(this.codigoFactura);
        hash = 23 * hash + Objects.hashCode(this.nitTienda);
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
        final Venta other = (Venta) obj;
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
        return "Venta{" + "fecha=" + fecha + ", nombreTienda=" + nombreTienda + ", codigoFactura=" + codigoFactura + ", nitTienda=" + nitTienda + '}';
    }

}
