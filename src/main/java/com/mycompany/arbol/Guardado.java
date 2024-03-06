/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol;

/**
 *
 * @author Samir
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.servlet.ServletContext;

import java.io.*;
import javax.servlet.ServletContext;

public class Guardado implements Serializable {

    public static Directorio directorioContactos;

    public static void guardarContacto(Directorio contactos, ServletContext context) throws IOException {
        String relativePath = "/data/contactos.ser";
        String absPath = context.getRealPath(relativePath);
        File archivo = new File(absPath);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(contactos);
            System.out.println("Datos de contactos guardados exitosamente en: contactos.ser");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos de contacto: " + e.getMessage());
        }
    }

    public static Directorio cargarContactos(ServletContext context) throws IOException, ClassNotFoundException {
        Directorio directorioContactos = null;
        String relativePath = "/data/contactos.ser";
        String absPath = context.getRealPath(relativePath);
        File archivo = new File(absPath);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            directorioContactos = (Directorio) ois.readObject();
            System.out.println("Datos de contactos cargados exitosamente desde: contactos.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos de contactos: " + e.getMessage());
        }

        if (directorioContactos == null) {
            directorioContactos = new Directorio();
        }

        return directorioContactos;
    }
}
