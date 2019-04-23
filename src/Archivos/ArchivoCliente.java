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

/**
 *
 * @author SEBAS
 */
public class ArchivoCliente {

    File nuevoArchivo = new File("archClientes.txt");
    RandomAccessFile archivo;

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

    public long indexOf(String identificacion) {
        try {
            boolean finArchivo = false;
            long posicionActual = archivo.getFilePointer();
            archivo.seek(0);

            do {
                try {
                    long posicion = archivo.getFilePointer();
                    archivo.readBoolean();
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
            System.out.println("ya existe un cliente con el numero de identificacion: "+nuevoCliente.identiicacion);
        }

    }

}
