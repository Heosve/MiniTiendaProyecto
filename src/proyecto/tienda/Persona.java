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
public class Persona {

    
    public String NumeroTelefono;
    public String identiicacion;
    public String nombre;
    public String direccion;

    public Persona() {
    }

    public Persona(String identiicacion, String nombre, String direccion, String NumeroTelefono) {
        this.identiicacion = identiicacion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.NumeroTelefono = NumeroTelefono;
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
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.NumeroTelefono);
        hash = 73 * hash + Objects.hashCode(this.identiicacion);
        hash = 73 * hash + Objects.hashCode(this.nombre);
        hash = 73 * hash + Objects.hashCode(this.direccion);
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.NumeroTelefono, other.NumeroTelefono)) {
            return false;
        }
        if (!Objects.equals(this.identiicacion, other.identiicacion)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "NumeroTelefono=" + NumeroTelefono + ", identiicacion=" + identiicacion + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }

}
