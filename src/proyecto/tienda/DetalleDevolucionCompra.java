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
public class DetalleDevolucionCompra {

    private DetalleCompra unDetalleCompra;
    private DevolucionCompra unaDevolucionCompra;

    public DetalleDevolucionCompra() {
    }

    public DetalleDevolucionCompra(DetalleCompra unDetalleCompra, DevolucionCompra unaDevolucionCompra) {
        this.unDetalleCompra = unDetalleCompra;
        this.unaDevolucionCompra = unaDevolucionCompra;
    }

    public DetalleCompra getUnDetalleCompra() {
        return unDetalleCompra;
    }

    public void setUnDetalleCompra(DetalleCompra unDetalleCompra) {
        this.unDetalleCompra = unDetalleCompra;
    }

    public DevolucionCompra getUnaDevolucionCompra() {
        return unaDevolucionCompra;
    }

    public void setUnaDevolucionCompra(DevolucionCompra unaDevolucionCompra) {
        this.unaDevolucionCompra = unaDevolucionCompra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.unDetalleCompra);
        hash = 37 * hash + Objects.hashCode(this.unaDevolucionCompra);
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
        final DetalleDevolucionCompra other = (DetalleDevolucionCompra) obj;
        if (!Objects.equals(this.unDetalleCompra, other.unDetalleCompra)) {
            return false;
        }
        if (!Objects.equals(this.unaDevolucionCompra, other.unaDevolucionCompra)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleDevolucionCompra{" + "unDetalleCompra=" + unDetalleCompra + ", unaDevolucionCompra=" + unaDevolucionCompra + '}';
    }

}
