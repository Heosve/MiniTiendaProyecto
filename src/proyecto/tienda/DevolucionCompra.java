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
public class DevolucionCompra {

    private Compra unaCompra;

    public DevolucionCompra() {
    }

    public DevolucionCompra(Compra unaCompra) {
        this.unaCompra = unaCompra;
    }

    public Compra getUnaCompra() {
        return unaCompra;
    }

    public void setUnaCompra(Compra unaCompra) {
        this.unaCompra = unaCompra;
    }

    public boolean devolucionCompra(boolean devolucion) {

        return devolucion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.unaCompra);
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
        final DevolucionCompra other = (DevolucionCompra) obj;
        if (!Objects.equals(this.unaCompra, other.unaCompra)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DevolucionCompra{" + "unaCompra=" + unaCompra + '}';
    }

}
