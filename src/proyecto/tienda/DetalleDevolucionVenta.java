/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.tienda;

import java.util.Objects;

/**
 *
 * @author Acer
 */
public class DetalleDevolucionVenta {

    private DetalleVenta unDetalleDeVenta;
    private DevolucionVenta unaDevolucionDeVenta;

    public DetalleDevolucionVenta() {
    }

    public DetalleDevolucionVenta(DetalleVenta unDetalleDeVenta, DevolucionVenta unaDevolucionDeVenta) {
        this.unDetalleDeVenta = unDetalleDeVenta;
        this.unaDevolucionDeVenta = unaDevolucionDeVenta;
    }

    public DetalleVenta getUnDetalleDeVenta() {
        return unDetalleDeVenta;
    }

    public void setUnDetalleDeVenta(DetalleVenta unDetalleDeVenta) {
        this.unDetalleDeVenta = unDetalleDeVenta;
    }

    public DevolucionVenta getUnaDevolucionDeVenta() {
        return unaDevolucionDeVenta;
    }

    public void setUnaDevolucionDeVenta(DevolucionVenta unaDevolucionDeVenta) {
        this.unaDevolucionDeVenta = unaDevolucionDeVenta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.unDetalleDeVenta);
        hash = 41 * hash + Objects.hashCode(this.unaDevolucionDeVenta);
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
        final DetalleDevolucionVenta other = (DetalleDevolucionVenta) obj;
        if (!Objects.equals(this.unDetalleDeVenta, other.unDetalleDeVenta)) {
            return false;
        }
        if (!Objects.equals(this.unaDevolucionDeVenta, other.unaDevolucionDeVenta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleDevolucionVenta{" + "unDetalleDeVenta=" + unDetalleDeVenta + ", unaDevolucionDeVenta=" + unaDevolucionDeVenta + '}';
    }

}
