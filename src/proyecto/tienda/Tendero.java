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
public class Tendero extends Persona {

    public String nombreTienda;
    public boolean credito;

    public Tendero() {
    }

    public Tendero(String nombreTienda, boolean credito) {
        this.nombreTienda = nombreTienda;
        this.credito = credito;
    }

    public Tendero(String nombreTienda, boolean credito, String identiicacion, String nombre, String direccion, String NumeroTelefono) {
        super(identiicacion, nombre, direccion, NumeroTelefono);
        this.nombreTienda = nombreTienda;
        this.credito = credito;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }

    public String getNumeroTelefono() {
        return NumeroTelefono;
    }

    public void setNumeroTelefono(String NumeroTelefono) {
        this.NumeroTelefono = NumeroTelefono;
    }

    public String getIdentiicacion() {
        return identiicacion;
    }

    public void setIdentiicacion(String identiicacion) {
        this.identiicacion = identiicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.nombreTienda);
        hash = 53 * hash + (this.credito ? 1 : 0);
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
        final Tendero other = (Tendero) obj;
        if (this.credito != other.credito) {
            return false;
        }
        if (!Objects.equals(this.nombreTienda, other.nombreTienda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tendero{" + "nombreTienda=" + nombreTienda + ", credito=" + credito + '}';
    }

    
  
 
}
