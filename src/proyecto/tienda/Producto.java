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
    private Date fecha;
    private int cantidad;
    private double precio;
    private double iva;
    private double precionConIva;

    //Constructor por defecto 
    public Producto() {
    }
//Constructor por parametros

    public double getPrecionConIva() {
        return precionConIva;
    }

    public void setPrecionConIva(double precionConIva) {
        this.precionConIva = precionConIva;
    }

    public Producto(String nombre, String codigo, Date fecha, int cantidad, double precio, double iva, double precioConIva) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
        this.iva = iva;
        this.precionConIva = precioConIva;
    }
// Getters y Setters (Recordemos que los getters sirven para mostrar el dato y los setters para cambiarlos)

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
// El Has

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.codigo);
        hash = 53 * hash + Objects.hashCode(this.fecha);
        hash = 53 * hash + this.cantidad;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.iva) ^ (Double.doubleToLongBits(this.iva) >>> 32));
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
        return "Producto{" + "nombre=" + nombre + ", codigo=" + codigo + ", fecha=" + fecha + ", cantidad=" + cantidad + ", precio=" + precio + ", iva=" + iva + '}';
    }

}
