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
public class AbonoVenta {

    private String tipoAbonoVenta;

    public AbonoVenta() {
    }

    public AbonoVenta(String tipoAbonoVenta) {
        this.tipoAbonoVenta = tipoAbonoVenta;
    }

    public String getTipoAbonoVenta() {
        return tipoAbonoVenta;
    }

    public void setTipoAbonoVenta(String tipoAbonoVenta) {
        this.tipoAbonoVenta = tipoAbonoVenta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.tipoAbonoVenta);
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
        final AbonoVenta other = (AbonoVenta) obj;
        if (!Objects.equals(this.tipoAbonoVenta, other.tipoAbonoVenta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AbonoVenta{" + "tipoAbonoVenta=" + tipoAbonoVenta + '}';
    }

}
