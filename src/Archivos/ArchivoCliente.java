/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import proyecto.tienda.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author SEBAS
 */
public class ArchivoCliente {

    File nuevoArchivo = new File("archClientes.txt");
    RandomAccessFile archivo;
    ArrayList<Cliente> clientes = new ArrayList<>();

    public ArchivoCliente() {
    }

    public ArchivoCliente(RandomAccessFile archivo) {
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

    public Cliente leer() {
        Cliente nuevoCliente = new Cliente();
        try {

            nuevoCliente.setCredito(archivo.readBoolean());
            nuevoCliente.setIdentiicacion(archivo.readUTF());
            nuevoCliente.setNombre(archivo.readUTF());
            nuevoCliente.setDireccion(archivo.readUTF());
            nuevoCliente.setNumeroTelefono(archivo.readUTF());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return nuevoCliente;
    }

    public void esccribir(Cliente nuevoCliente) {
        try {
            archivo.writeBoolean(nuevoCliente.isCredito());
            archivo.writeUTF(nuevoCliente.identiicacion);
            archivo.writeUTF(nuevoCliente.nombre);
            archivo.writeUTF(nuevoCliente.direccion);
            archivo.writeUTF(nuevoCliente.NumeroTelefono);
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

    public void agregar(Cliente nuevoCliente) {
        try {
            if (indexOf(nuevoCliente.identiicacion) != -1) {

                archivo.seek(archivo.length());
                esccribir(nuevoCliente);
            }
        } catch (IOException e) {
            System.out.println("ya existe un cliente con el numero de identificacion: " + nuevoCliente.identiicacion);
        }

    }

    public Cliente buscar(String identificacion) {
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);

            do {
                try {
                    boolean credito = archivo.readBoolean();
                    long posicion = archivo.getFilePointer();
                    String identificacionActual = archivo.readUTF();
                    String nombre = archivo.readUTF();
                    String direccion = archivo.readUTF();
                    String numeroTelefono = archivo.readUTF();

                    if (identificacion.equals(identificacionActual)) {
                        archivo.seek(posicion);
                        return new Cliente(credito, identificacion, nombre, direccion, numeroTelefono);
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
            clientes = null;

            do {
                try {
                    boolean credito = archivo.readBoolean();
                    String identificacionActual = archivo.readUTF();
                    String nombre = archivo.readUTF();
                    String direccion = archivo.readUTF();
                    String numeroTelefono = archivo.readUTF();

                    if (identificacionActual.equals(identificacion)) {
                        borrado = true;
                    } else {
                        clientes.add(new Cliente(credito, identificacion, nombre, direccion, numeroTelefono));
                    }
                } catch (IOException e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);

            nuevoArchivo.delete();
            archivo.close();
            abrir("rw");
            arrayToArchivo(clientes);
            archivo.seek(posicionActual);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return borrado;
    }

    public RandomAccessFile arrayToArchivo(ArrayList<Cliente> clientes) {
        try {
            archivo.seek(0);
            for (Cliente c : clientes) {
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
    public boolean modificar(String identificacionAnterior, String identificacion, String nombre,
            String direccion, String numeroTelefono) {

        boolean modificado = false;
        try {
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

    public ArrayList<Cliente> getLista() {
        boolean finArchivo = false;
        clientes = null;
        try {
            long posicion = archivo.getFilePointer();
            archivo.seek(0);
            do {
                try {
                    clientes.add(leer());
                } catch (Exception e) {
                    finArchivo = true;
                }
            } while (finArchivo==false);
            archivo.seek(posicion);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
            return clientes;
        }
    
    

    public void close() throws IOException {
        archivo.close();
    }

}
