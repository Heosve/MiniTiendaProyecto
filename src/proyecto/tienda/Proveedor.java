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
public class Proveedor extends Persona {

    private String nombreEmpresa;
    private String email;

    public Proveedor() {
    }

    public Proveedor(String nombreEmpresa, String email) {
        this.nombreEmpresa = nombreEmpresa;
        this.email = email;
    }

    public Proveedor(String nombreEmpresa, String email, String identiicacion, String nombre, String direccion, String NumeroTelefono) {
        super(identiicacion, nombre, direccion, NumeroTelefono);
        this.nombreEmpresa = nombreEmpresa;
        this.email = email;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash = 47 * hash + Objects.hashCode(this.nombreEmpresa);
        hash = 47 * hash + Objects.hashCode(this.email);
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
        final Proveedor other = (Proveedor) obj;
        if (!Objects.equals(this.nombreEmpresa, other.nombreEmpresa)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "nombreEmpresa=" + nombreEmpresa + ", email=" + email + '}';
    }

 
}
