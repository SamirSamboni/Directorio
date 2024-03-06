/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol;

/**
 *
 * @author Brian
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Directorio implements Serializable {

    private Contacto contactoRaiz;

    private int numContactos;

    public Directorio() {
        contactoRaiz = null;
        numContactos = 0;
    }

    public ArrayList<Contacto> obtenerContactos() {
        ArrayList<Contacto> listaContactos = new ArrayList<>();
        agregarContactoLista(contactoRaiz, listaContactos);
        return listaContactos;
    }

    private void agregarContactoLista(Contacto contacto, ArrayList<Contacto> listaContactos) {
        if (contacto != null) {
            agregarContactoLista(contacto.getIzq(), listaContactos);
            listaContactos.add(contacto);
            agregarContactoLista(contacto.getDer(), listaContactos);
        }
    }

    private boolean contactoExistenteConNombre(Contacto contacto, String nombre) {
        if (contacto == null) {
            return false;
        }

        int comparacion = nombre.compareTo(contacto.getNombre());

        if (comparacion == 0) {
            return true;
        } else if (comparacion < 0) {
            return contactoExistenteConNombre(contacto.getIzq(), nombre);
        } else {
            return contactoExistenteConNombre(contacto.getDer(), nombre);
        }
    }

    public void agregarContacto(int idContacto, String nombre, String apellido, String celular, String direccion, String eMail)
            throws ContactoRepetidoException {
        Contacto nuevoContacto = new Contacto(idContacto, nombre, apellido, celular, direccion, eMail, null, null);

        if (contactoExistenteConNombre(contactoRaiz, nombre)) {
            throw new ContactoRepetidoException(nombre);
        }

        if (contactoRaiz == null) {
            contactoRaiz = nuevoContacto;
        } else {
            contactoRaiz.insertar(nuevoContacto);
        }

        numContactos++;
    }

    public void deleteContact(String nombre) {
        contactoRaiz = contactoRaiz.eliminar(nombre);
        numContactos--;

    }

    public boolean editContacto(int idContacto, String nombre, String apellido, String celular, String direccion, String eMail) {
        Contacto contacto = searchContact(nombre);

        if (contacto != null) {

            contacto.setIdContacto(idContacto);
            contacto.setApellido(apellido);
            contacto.setCelular(celular);
            contacto.setDireccion(direccion);
            contacto.seteMail(eMail);

            return true;
        }

        return false;
    }

    public Contacto searchContact(String nombre) {
        return contactoRaiz == null ? null : contactoRaiz.buscar(nombre);
    }

    /**
     * Retorna el número de contactos que están en el directorio.
     *
     * @return El número de contactos presentes en el árbol.
     */
    public int darPeso() {
        return contactoRaiz == null ? 0 : contactoRaiz.darPeso();
    }

}
