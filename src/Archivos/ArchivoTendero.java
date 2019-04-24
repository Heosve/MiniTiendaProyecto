/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import proyecto.tienda.Tendero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author SEBAS
 */
public class ArchivoTendero {

    File nuevoArchivo = new File("archEmpleados.txt");
    RandomAccessFile archivo;
    ArrayList<Tendero> tenderos = new ArrayList<>(100);

    public ArchivoTendero() {
    }

    public ArchivoTendero(RandomAccessFile archivo) {
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

    public Tendero leer() {
        Tendero nuevoTendero = new Tendero();
        try {

            nuevoTendero.setNombreTienda(archivo.readUTF());
            nuevoTendero.setCredito(archivo.readBoolean());
            nuevoTendero.setIdentiicacion(archivo.readUTF());
            nuevoTendero.setNombre(archivo.readUTF());
            nuevoTendero.setDireccion(archivo.readUTF());
            nuevoTendero.setNumeroTelefono(archivo.readUTF());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return nuevoTendero;
    }

    public void esccribir(Tendero nuevoTendero) {
        try {
            archivo.writeUTF(nuevoTendero.nombreTienda);
            archivo.writeBoolean(nuevoTendero.isCredito());
            archivo.writeUTF(nuevoTendero.identiicacion);
            archivo.writeUTF(nuevoTendero.nombre);
            archivo.writeUTF(nuevoTendero.direccion);
            archivo.writeUTF(nuevoTendero.NumeroTelefono);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Retorna la posición en la que se encuentra un cliente con identificación
     * igual al del argumento. Si no se encuentra, retorna -1 El puntero del
     * archivo queda posicionado al inicio de la identificación del cliente
     */
    public long indexOf(String identificacion) {
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);

            do {
                try {
                    archivo.readUTF();
                    archivo.readBoolean();
                    long posicion = archivo.getFilePointer();
                    String identificacionActual = archivo.readUTF();
                    if (identificacion.equals(identificacionActual)) {
                        archivo.seek(posicion);
                        return posicion;
                    }
                    archivo.readUTF();      // Lee nombre
                    archivo.readUTF();      // Lee dirección
                    archivo.readUTF();      // Lee numero de telefono
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

    public void agregar(Tendero nuevoTendero) {
        try {
            if (indexOf(nuevoTendero.identiicacion) != -1) {

                archivo.seek(archivo.length());
                esccribir(nuevoTendero);
            }
        } catch (IOException e) {
            System.out.println("ya existe un empleado con el numero de identificacion: " + nuevoTendero.identiicacion);
        }

    }

    public Tendero buscar(String identificacion) {
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);

            do {
                try {
                    String nombreTienda=archivo.readUTF();
                    boolean credito = archivo.readBoolean();
                    long posicion = archivo.getFilePointer();
                    String identificacionActual = archivo.readUTF();
                    String nombre = archivo.readUTF();
                    String direccion = archivo.readUTF();
                    String numeroTelefono = archivo.readUTF();

                    if (identificacion.equals(identificacionActual)) {
                        archivo.seek(posicion);
                        return new Tendero(nombreTienda, credito, identificacion, nombre, direccion, numeroTelefono);
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
    public boolean eliminar(String identificacion) {

        boolean borrado = false;
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);
            tenderos = null;

            do {
                try {
                    String nombreTienda = archivo.readUTF();
                    boolean credito = archivo.readBoolean();
                    String identificacionActual = archivo.readUTF();
                    String nombre = archivo.readUTF();
                    String direccion = archivo.readUTF();
                    String numeroTelefono = archivo.readUTF();

                    if (identificacionActual.equals(identificacion)) {
                        borrado = true;
                    } else {
                        tenderos.add(new Tendero(nombreTienda, credito, identificacion, nombre, direccion, numeroTelefono));
                    }
                } catch (IOException e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);

            nuevoArchivo.delete();
            archivo.close();
            abrir("rw");
            tenderos.trimToSize();
            arrayToArchivo(tenderos);
            archivo.seek(posicionActual);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return borrado;
    }

    public RandomAccessFile arrayToArchivo(ArrayList<Tendero> tenderos) {
        try {
            archivo.seek(0);
            for (Tendero c : tenderos) {
                archivo.writeUTF(c.getNombreTienda());
                archivo.writeBoolean(c.isCredito());
                archivo.writeUTF(c.getIdentiicacion());
                archivo.writeUTF(c.getNombre());
                archivo.writeUTF(c.getDireccion());
                archivo.writeUTF(c.getNumeroTelefono());
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
     */
    public boolean modificar(String identificacionAnterior, String nombreTienda,String identificacion, String nombre,
            String direccion, String numeroTelefono) {

        boolean modificado = false;
        try {
            archivo.writeUTF(nombreTienda);
            long posicion = indexOf(identificacionAnterior);
            archivo.seek(posicion);
            archivo.writeUTF(identificacion);
            archivo.writeUTF(nombre);
            archivo.writeUTF(direccion);
            archivo.writeUTF(numeroTelefono);

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

    public ArrayList<Tendero> getLista() {
        boolean finArchivo = false;
        tenderos = null;
        try {
            long posicion = archivo.getFilePointer();
            archivo.seek(0);
            do {
                try {
                    tenderos.add(leer());
                } catch (Exception e) {
                    finArchivo = true;
                }
            } while (finArchivo==false);
            archivo.seek(posicion);
            tenderos.trimToSize();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
            return tenderos;
        }
    
    

    public void close() throws IOException {
        archivo.close();
    }

}
