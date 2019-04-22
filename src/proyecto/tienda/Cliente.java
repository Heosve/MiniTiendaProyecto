/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.tienda;

/**
 *
 * @author Acer
 */
public class Cliente extends Persona {

    private boolean credito;

    public Cliente() {
    }

    public Cliente(boolean credito) {
        this.credito = credito;
    }

    public Cliente(boolean credito, String identiicacion, String nombre, String direccion, String NumeroTelefono) {
        super(identiicacion, nombre, direccion, NumeroTelefono);
        this.credito = credito;
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
        int hash = 7;
        hash = 17 * hash + (this.credito ? 1 : 0);
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
        final Cliente other = (Cliente) obj;
        if (this.credito != other.credito) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "credito=" + credito + '}';
    }

  
    
}
