package Archivos;

import proyecto.tienda.Producto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author SEBAS Y JUAN
 */
public class ArchivoProducto {

    File nuevoArchivo = new File("archproducto.txt");
    RandomAccessFile archivo;
    ArrayList<Producto> productos = new ArrayList<>(100);

    public ArchivoProducto() {
    }

    public ArchivoProducto(RandomAccessFile archivo) {
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

    public Producto leer() {
        Producto nuevoProducto = new Producto();
        try {

            nuevoProducto.setNombre(archivo.readUTF());
            nuevoProducto.setCodigo(archivo.readUTF());
            nuevoProducto.setFecha(archivo.readUTF());
            nuevoProducto.setCantidad(archivo.readInt());
            nuevoProducto.setPrecio(archivo.readDouble());
            nuevoProducto.setIva(archivo.readDouble());
            nuevoProducto.setPrecionConIva(archivo.readDouble());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return nuevoProducto;
    }

    public void esccribir(Producto nuevoproducto) {
        try {
            archivo.writeUTF(nuevoproducto.getNombre());
            archivo.writeUTF(nuevoproducto.getCodigo());
            archivo.writeUTF(nuevoproducto.getFecha());
            archivo.writeInt(nuevoproducto.getCantidad());
            archivo.writeDouble(nuevoproducto.getPrecio());
            archivo.writeDouble(nuevoproducto.getIva());
            archivo.writeDouble(nuevoproducto.getPrecionConIva());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Retorna la posición en la que se encuentra un cliente con identificación
     * igual al del argumento. Si no se encuentra, retorna -1 El puntero del
     * archivo queda posicionado al inicio de la identificación del cliente
     */
    public long indexOf(String Codigo) {
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);

            do {
                try {
                    archivo.readUTF();
                    long posicion = archivo.getFilePointer();
                    String CodigoActual = archivo.readUTF();
                    if (Codigo.equals(CodigoActual)) {
                        archivo.seek(posicion);
                        return posicion;
                    }
                    archivo.readUTF(); // Lee Fecha
                    archivo.readInt(); //Lee Cantidad
                    archivo.readDouble();//Lee Precio
                    archivo.readDouble();//Lee Iva
                    archivo.readDouble();//Lee PrecioConIva

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

    public void agregar(Producto nuevoProducto) {
        try {
            if (indexOf(nuevoProducto.getCodigo()) != -1) {

                archivo.seek(archivo.length());
                esccribir(nuevoProducto);
            }
        } catch (IOException e) {
            System.out.println("ya existe un producto con el numero de codigo: " + nuevoProducto.getCodigo());
        }

    }

    public Producto buscar(String codigo) {
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);

            do {
                try {
                    String nombre = archivo.readUTF();
                    long posicion = archivo.getFilePointer();
                    String CodigoActual = archivo.readUTF();
                    String fecha = archivo.readUTF();
                    int cantidad = archivo.readInt();
                    double precio = archivo.readDouble();
                    double iva = archivo.readDouble();
                    double precioconiva = archivo.readDouble();

                    if (codigo.equals(CodigoActual)) {
                        archivo.seek(posicion);
                        return new Producto(nombre, CodigoActual, fecha, cantidad, precio, iva, precioconiva);
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
    public boolean eliminar(String codigo) {

        boolean borrado = false;
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);
            productos = null;

            do {
                try {
                    String nombre = archivo.readUTF();
                    String CodigoActual = archivo.readUTF();
                    String fecha = archivo.readUTF();
                    int cantidad = archivo.readInt();
                    double precio = archivo.readDouble();
                    double iva = archivo.readDouble();
                    double precioConIva = archivo.readDouble();
                    if (CodigoActual.equals(codigo)) {
                        borrado = true;
                    } else {
                        productos.add(new Producto(nombre, codigo, fecha, cantidad, precio, iva, precioConIva));
                    }
                } catch (IOException e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);

            nuevoArchivo.delete();
            archivo.close();
            abrir("rw");
            productos.trimToSize();
            arrayToArchivo(productos);
            archivo.seek(posicionActual);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return borrado;
    }

    public RandomAccessFile arrayToArchivo(ArrayList<Producto> productos) {
        try {
            archivo.seek(0);
            for (Producto p : productos) {
                archivo.writeUTF(p.getNombre());
                archivo.writeUTF(p.getCodigo());
                archivo.writeUTF(p.getFecha());
                archivo.writeInt(p.getCantidad());
                archivo.writeDouble(p.getPrecio());
                archivo.writeDouble(p.getIva());
                archivo.writeDouble(p.getPrecionConIva());
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
    public boolean modificar(String codigoAnterior, String codigo, String nombre,
            String fecha, int cantidad, double precio, double iva, double precioconiva) {

        boolean modificado = false;
        try {
            long posicion = indexOf(codigoAnterior);
            archivo.seek(posicion);
            archivo.readUTF();   
            archivo.readUTF();  
            archivo.readUTF(); 
            archivo.readInt(); 
            archivo.readDouble();
            archivo.readDouble();
            archivo.readDouble();
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
    public ArrayList<Producto> getLista() {
        boolean finArchivo = false;
        productos = null;
        try {
            long posicion = archivo.getFilePointer();
            archivo.seek(0);
            do {
                try {
                    productos.add(leer());
                } catch (Exception e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);
            archivo.seek(posicion);
            productos.trimToSize();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    public void close() throws IOException {
        archivo.close();
    }

}
