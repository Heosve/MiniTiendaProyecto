/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.tienda;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Acer
 */
//Estamos Defiendo Variables
public class Producto {

    private String nombre;
    private String codigo;
    private String fecha;
    private int cantidad;
    private double precio;
    private double iva;
    private double precionConIva;

    public Producto() {
    }

    public Producto(String nombre, String codigo, String fecha, int cantidad, double precio, double iva, double precionConIva) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
        this.iva = iva;
        this.precionConIva = precionConIva;
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPrecionConIva() {
        return precionConIva;
    }

    public void setPrecionConIva(double precionConIva) {
        this.precionConIva = precionConIva;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.codigo);
        hash = 29 * hash + Objects.hashCode(this.fecha);
        hash = 29 * hash + this.cantidad;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.iva) ^ (Double.doubleToLongBits(this.iva) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.precionConIva) ^ (Double.doubleToLongBits(this.precionConIva) >>> 32));
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
        final Producto other = (Producto) obj;
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (Double.doubleToLongBits(this.iva) != Double.doubleToLongBits(other.iva)) {
            return false;
        }
        if (Double.doubleToLongBits(this.precionConIva) != Double.doubleToLongBits(other.precionConIva)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", codigo=" + codigo + ", fecha=" + fecha + ", cantidad=" + cantidad + ", precio=" + precio + ", iva=" + iva + ", precionConIva=" + precionConIva + '}';
    }

}
