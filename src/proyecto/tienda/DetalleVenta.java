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
public class DetalleVenta {

    private int cantidad;
    private double subtotal;
    private double iva;
    private Producto unProducto;
    private double total;

    public DetalleVenta() {
    }

    public DetalleVenta(int cantidad, double subtotal, double iva, Producto unProducto, double total) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.iva = iva;
        this.unProducto = unProducto;
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Producto getUnProducto() {
        return unProducto;
    }

    public void setUnProducto(Producto unProducto) {
        this.unProducto = unProducto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.cantidad;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.subtotal) ^ (Double.doubleToLongBits(this.subtotal) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.iva) ^ (Double.doubleToLongBits(this.iva) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.unProducto);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
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
        final DetalleVenta other = (DetalleVenta) obj;
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (Double.doubleToLongBits(this.subtotal) != Double.doubleToLongBits(other.subtotal)) {
            return false;
        }
        if (Double.doubleToLongBits(this.iva) != Double.doubleToLongBits(other.iva)) {
            return false;
        }
        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
            return false;
        }
        if (!Objects.equals(this.unProducto, other.unProducto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "cantidad=" + cantidad + ", subtotal=" + subtotal + ", iva=" + iva + ", unProducto=" + unProducto + ", total=" + total + '}';
    }

}
