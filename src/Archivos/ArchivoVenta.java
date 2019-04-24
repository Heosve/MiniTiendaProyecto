package Archivos;

import proyecto.tienda.Venta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author SEBAS Y JUAN
 */
public class ArchivoVenta {

    File nuevoArchivo = new File("archventa.txt");
    RandomAccessFile archivo;
    ArrayList<Venta> ventas = new ArrayList<>();

    public ArchivoVenta() {
    }

    public ArchivoVenta(RandomAccessFile archivo) {
        this.archivo = archivo;
    }

    public File getNuevoArchivo() {
        return nuevoArchivo;
    }

    public void setNuevoArchivo(File nuevoArchivo) {
        this.nuevoArchivo = nuevoArchivo;
    }

    public void abrir(String modo) {

        try {
            archivo = new RandomAccessFile(nuevoArchivo, modo);

        } catch (FileNotFoundException e) {
            System.out.println("no se puede abrir el archivo: " + e.getMessage());
        }
    }

    public Venta leer() {
        Venta nuevaVenta = new Venta();
        try {

            nuevaVenta.setFecha(archivo.readUTF());
            nuevaVenta.setNombreTienda(archivo.readUTF());
            nuevaVenta.setCodigoFactura(archivo.readUTF());
            nuevaVenta.setNitTienda(archivo.readUTF());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return nuevaVenta;
    }

    public void esccribir(Venta nuevaventa) {
        try {
            archivo.writeUTF(nuevaventa.getFecha());
            archivo.writeUTF(nuevaventa.getNombreTienda());
            archivo.writeUTF(nuevaventa.getCodigoFactura());
            archivo.writeUTF(nuevaventa.getNitTienda());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Retorna la posición en la que se encuentra un cliente con identificación
     * igual al del argumento. Si no se encuentra, retorna -1 El puntero del
     * archivo queda posicionado al inicio de la identificación del cliente
     */
    public long indexOf(String CodigoFactura) {
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);

            do {
                try {
                    archivo.readUTF(); //Lee Fecha
                    archivo.readUTF(); //LeeNombreTienda
                    long posicion = archivo.getFilePointer();
                    String CodigoActual = archivo.readUTF();
                    if (CodigoFactura.equals(CodigoActual)) {
                        archivo.seek(posicion);
                        return posicion;
                    }
                    archivo.readUTF(); // nit Tienda Fecha

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    finArchivo = true;
                }
            } while (finArchivo == false);
            archivo.seek(posicionActual);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public void agregar(Venta nuevaVenta) {
        try {
            if (indexOf(nuevaVenta.getCodigoFactura()) != -1) {

                archivo.seek(archivo.length());
                esccribir(nuevaVenta);
            }
        } catch (IOException e) {
            System.out.println("ya existe una venta  con ese numero de codigo: " + nuevaVenta.getCodigoFactura());
        }

    }

    public Venta buscar(String codigoFactura) {
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);

            do {
                try {
                    String fecha = archivo.readUTF();
                    String nombreTienda = archivo.readUTF();
                    long posicion = archivo.getFilePointer();
                    String CodigoActual = archivo.readUTF();
                    String nitTienda= archivo.readUTF();

                    if (codigoFactura.equals(CodigoActual)) {
                        archivo.seek(posicion);
                        return new Venta(fecha, nombreTienda, nombreTienda,codigoFactura, nitTienda);
                    }
                } catch (IOException e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);
            archivo.seek(posicionActual);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Elimina el cliente cuyo código corresponda al del argumento. La
     * eliminación no tiene éxito, si existen otros archivos relacionados con el
     * cliente que se pretende eliminar.
     */
    public boolean eliminar(String codigoFactura) {

        boolean borrado = false;
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);
            ventas = null;

            do {
                try {
                    String fecha = archivo.readUTF();
                    String nombreTienda= archivo.readUTF();
                    String CodigoActual = archivo.readUTF();
                    String nitTienda = archivo.readUTF();
                   
                    if (CodigoActual.equals(codigoFactura)) {
                        borrado = true;
                    } else {
                        ventas.add(new Venta(fecha, nombreTienda, codigoFactura, nitTienda));
                    }
                } catch (IOException e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);

            nuevoArchivo.delete();
            archivo.close();
            abrir("rw");
            arrayToArchivo(ventas);
            archivo.seek(posicionActual);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return borrado;
    }

    public RandomAccessFile arrayToArchivo(ArrayList<Venta> Ventas) {
        try {
            archivo.seek(0);
            for (Venta v : Ventas) {
                archivo.writeUTF(v.getFecha());
                archivo.writeUTF(v.getNombreTienda());
                archivo.writeUTF(v.getCodigoFactura());
                archivo.writeUTF(v.getNitTienda());
                            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return archivo;
    }

    /**
     * Modifica el cliente cuyo identificacion se da como primer argumento. Para
     * mantener la integridad referencial se evita modificar el código del
     * registro. Argumentos: identificacion: el código que se buscará en el
     * archivo cliente: un objeto que contiene los nuevos datos del cliente
     *
     *
     */
    public boolean modificar(String codigoAnterior, String fecha, String nombreTienda,
      String codigoFactura ,String nitTienda ) {

        boolean modificado = false;
        try {
            archivo.writeUTF(fecha);
            archivo.writeUTF(nombreTienda);
            long posicion = indexOf(codigoAnterior);
            archivo.seek(posicion);
             archivo.writeUTF(codigoFactura);
            archivo.writeUTF(nitTienda);
       
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return modificado;
    }

    /**
     * Retorna una lista con todos los clientes que se encuentran almacenados en
     * el archivo respectivo. Si no hay registros se retorna la lista vacía. El
     * puntero del archivo se reestablece a la posición que tenía antes de crear
     * la lista.
     */
    public ArrayList<Venta> getLista() {
        boolean finArchivo = false;
        ventas = null;
        try {
            long posicion = archivo.getFilePointer();
            archivo.seek(0);
            do {
                try {
                    ventas.add(leer());
                } catch (Exception e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);
            archivo.seek(posicion);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ventas;
    }

    public void close() throws IOException {
        archivo.close();
    }

}
