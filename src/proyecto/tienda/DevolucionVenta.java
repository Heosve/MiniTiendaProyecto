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
public class DevolucionVenta {

    private Venta unaVenta;

    public DevolucionVenta() {
    }

    public DevolucionVenta(Venta unaVenta) {
        this.unaVenta = unaVenta;
    }

    public Venta getUnaVenta() {
        return unaVenta;
    }

    public void setUnaVenta(Venta unaVenta) {
        this.unaVenta = unaVenta;
    }

    public boolean devolucionVenta(boolean devolucion) {

        return devolucion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.unaVenta);
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
        final DevolucionVenta other = (DevolucionVenta) obj;
        if (!Objects.equals(this.unaVenta, other.unaVenta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DevolucionVenta{" + "unaVenta=" + unaVenta + '}';
    }

}
