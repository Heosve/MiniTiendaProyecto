/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import proyecto.tienda.Proveedor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author SEBAS y Juan
 */
public class ArchivoProveedor {

    File nuevoArchivo = new File("archProveedor.txt");
    RandomAccessFile archivo;
    ArrayList<Proveedor> proveedores = new ArrayList<>(100);

    public ArchivoProveedor() {
    }

    public ArchivoProveedor(RandomAccessFile archivo) {
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

    public Proveedor leer() {
        Proveedor nuevoProveedor = new Proveedor();
        try {

            nuevoProveedor.setNombreEmpresa(archivo.readUTF());
            nuevoProveedor.setEmail(archivo.readUTF());
            nuevoProveedor.setIdentiicacion(archivo.readUTF());
            nuevoProveedor.setNombre(archivo.readUTF());
            nuevoProveedor.setDireccion(archivo.readUTF());
            nuevoProveedor.setNumeroTelefono(archivo.readUTF());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return nuevoProveedor;
    }

    public void esccribir(Proveedor nuevoProveedor) {
        try {
            archivo.writeUTF(nuevoProveedor.getNombreEmpresa());
            archivo.writeUTF(nuevoProveedor.getEmail());
            archivo.writeUTF(nuevoProveedor.identiicacion);
            archivo.writeUTF(nuevoProveedor.nombre);
            archivo.writeUTF(nuevoProveedor.direccion);
            archivo.writeUTF(nuevoProveedor.NumeroTelefono);
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
                    archivo.readUTF();      // Lee Nombre Empresa
                    archivo.readUTF();      // lee Email
                    long posicion = archivo.getFilePointer();
                    String identificacionActual = archivo.readUTF();
                    if (identificacion.equals(identificacionActual)) {
                        archivo.seek(posicion);
                        return posicion;
                    }
                         // Lee dirección
                    archivo.readUTF();
                    archivo.readUTF();
                    archivo.readUTF();
                   

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

    public void agregar(Proveedor nuevoProveedor) {
        try {
            if (indexOf(nuevoProveedor.identiicacion) != -1) {

                archivo.seek(archivo.length());
                esccribir(nuevoProveedor);
            }
        } catch (IOException e) {
            System.out.println("ya existe un cliente con el numero de identificacion: " + nuevoProveedor.identiicacion);
        }

    }

    public Proveedor buscar(String identificacion) {
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);

            do {
                try {
                    String nombreEmpresa = archivo.readUTF();
                    String email = archivo.readUTF();
                    long posicion = archivo.getFilePointer();
                    String identificacionActual = archivo.readUTF();
                    String nombre = archivo.readUTF();
                    String direccion = archivo.readUTF();
                    String numeroTelefono = archivo.readUTF();

                    if (identificacion.equals(identificacionActual)) {
                        archivo.seek(posicion);
                        return new Proveedor(nombreEmpresa, email, identificacion, nombre, direccion, numeroTelefono);
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
            proveedores = null;

            do {
                try {
                    String nombreEmpresa = archivo.readUTF();
                    String email = archivo.readUTF();
                    String identificacionActual = archivo.readUTF();
                    String nombre = archivo.readUTF();
                    String direccion = archivo.readUTF();
                    String numeroTelefono = archivo.readUTF();

                    if (identificacionActual.equals(identificacion)) {
                        borrado = true;
                    } else {
                        proveedores.add(new Proveedor(nombreEmpresa, email, identificacion, nombre, direccion, numeroTelefono));
                    }
                } catch (IOException e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);

            nuevoArchivo.delete();
            archivo.close();
            abrir("rw");
            proveedores.trimToSize();
            arrayToArchivo(proveedores);
            archivo.seek(posicionActual);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return borrado;
    }

    public RandomAccessFile arrayToArchivo(ArrayList<Proveedor> proveedores) {
        try {
            archivo.seek(0);
            for (Proveedor pr : proveedores) {
            archivo.writeUTF(pr.getNombreEmpresa());
            archivo.writeUTF(pr.getEmail());
            archivo.writeUTF(pr.identiicacion);
            archivo.writeUTF(pr.nombre);
            archivo.writeUTF(pr.direccion);
            archivo.writeUTF(pr.NumeroTelefono);
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
    public boolean modificar(String identificacionAnterior, String nombreEmpresa, String email,String identificacion, String nombre,
            String direccion, String numeroTelefono) {

        boolean modificado = false;
        try {
            long posicion = indexOf(identificacionAnterior);
            archivo.seek(posicion);
            archivo.writeUTF(nombreEmpresa);
            archivo.writeUTF(email);
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
    public ArrayList<Proveedor> getLista() {
        boolean finArchivo = false;
        proveedores = null;
        try {
            long posicion = archivo.getFilePointer();
            archivo.seek(0);
            do {
                try {
                    proveedores.add(leer());
                } catch (Exception e) {
                    finArchivo = true;
                }
            } while (finArchivo == false);
            archivo.seek(posicion);
            proveedores.trimToSize();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return proveedores;
    }

    public void close() throws IOException {
        archivo.close();
    }

}
