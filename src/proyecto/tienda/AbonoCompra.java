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
public class AbonoCompra {

    private String tipoAbono;

    public AbonoCompra() {
    }

    public AbonoCompra(String tipoAbono) {
        this.tipoAbono = tipoAbono;
    }

    public String getTipoAbono() {
        return tipoAbono;
    }

    public void setTipoAbono(String tipoAbono) {
        this.tipoAbono = tipoAbono;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.tipoAbono);
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
        final AbonoCompra other = (AbonoCompra) obj;
        if (!Objects.equals(this.tipoAbono, other.tipoAbono)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AbonoCompra{" + "tipoAbono=" + tipoAbono + '}';
    }

}
