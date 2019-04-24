package Archivos;

import proyecto.tienda.Compra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author SEBAS Y JUAN
 */
public class ArchivoCompra {

    File nuevoArchivo = new File("archcompra.txt");
    RandomAccessFile archivo;
    ArrayList<Compra> compras = new ArrayList<>(100);

    public ArchivoCompra() {
    }

    public ArchivoCompra(RandomAccessFile archivo) {
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

    public Compra leer() {
        Compra nuevaVenta = new Compra();
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

    public void esccribir(Compra nuevacompra) {
        try {
            archivo.writeUTF(nuevacompra.getFecha());
            archivo.writeUTF(nuevacompra.getNombreTienda());
            archivo.writeUTF(nuevacompra.getCodigoFactura());
            archivo.writeUTF(nuevacompra.getNitTienda());

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

    public void agregar(Compra nuevaCompra) {
        try {
            if (indexOf(nuevaCompra.getCodigoFactura()) != -1) {

                archivo.seek(archivo.length());
                esccribir(nuevaCompra);
            }
        } catch (IOException e) {
            System.out.println("ya existe una compra  con ese numero de codigo: " + nuevaCompra.getCodigoFactura());
        }

    }

    public Compra buscar(String codigoFactura) {
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
                        return new Compra(fecha, nombreTienda, nombreTienda,codigoFactura, nitTienda);
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
            compras = null;

            do {
                try {
                    String fecha = archivo.readUTF();
                    String nombreTienda= archivo.readUTF();
                    String CodigoActual = archivo.readUTF();
                    String nitTienda = archivo.readUTF();
                   
                    if (CodigoActual.equals(codigoFactura)) {
                        borrado = true;
                    } else {
                        compras.add(new Compra(fecha, nombreTienda, codigoFactura, nitTienda));
                    }
                } catch (IOException e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);

            nuevoArchivo.delete();
            archivo.close();
            abrir("rw");
            compras.trimToSize();
            arrayToArchivo(compras);
            archivo.seek(posicionActual);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return borrado;
    }

    public RandomAccessFile arrayToArchivo(ArrayList<Compra> compras) {
        try {
            archivo.seek(0);
            for (Compra cr : compras) {
                archivo.writeUTF(cr.getFecha());
                archivo.writeUTF(cr.getNombreTienda());
                archivo.writeUTF(cr.getCodigoFactura());
                archivo.writeUTF(cr.getNitTienda());
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
    public ArrayList<Compra> getLista() {
        boolean finArchivo = false;
        compras = null;
        try {
            long posicion = archivo.getFilePointer();
            archivo.seek(0);
            do {
                try {
                    compras.add(leer());
                } catch (Exception e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);
            archivo.seek(posicion);
            compras.trimToSize();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return compras;
    }

    public void close() throws IOException {
        archivo.close();
    }

}
