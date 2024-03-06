/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol;

/**
 *
 * @author Samir
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Contacto implements Comparable, Serializable {

    private int idContacto;

    private String nombre;

    private String apellido;

    private String celular;

    private String direccion;

    private String eMail;

    private Contacto izq;

    private Contacto der;

    public Contacto() {
    }

    public Contacto(int idContacto, String nombre, String apellido, String celular, String direccion, String eMail, Contacto izq, Contacto der) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.direccion = direccion;
        this.eMail = eMail;
        this.izq = null;
        this.der = null;
    }

    public int compareTo(Object o) {
        Contacto otro = (Contacto) o;
        return nombre.compareToIgnoreCase(otro.nombre);
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Contacto getIzq() {
        return izq;
    }

    public void setIzq(Contacto izq) {
        this.izq = izq;
    }

    public Contacto getDer() {
        return der;
    }

    public void setDer(Contacto der) {
        this.der = der;
    }

    public boolean esHoja() {
        return izq == null && der == null;
    }

    /**
     * Devuelve el contacto con el nombre más bajo en orden alfabético dentro
     * del árbol descendiente de este nodo.
     *
     * @return El contacto con el nombre más bajo.
     */
    public Contacto darMenor() {
        return (izq == null) ? this : izq.darMenor();
    }

    /**
     * Devuelve el contacto con el nombre más alto en orden alfabético dentro
     * del árbol descendiente de este nodo.
     *
     * @return El contacto con el nombre más alto.
     */
    public Contacto darMayor() {
        return (der == null) ? this : der.darMayor();
    }

    /**
     * Devuelve la altura del árbol de contactos que se inicia en este nodo.
     *
     * @return La altura del árbol que comienza en este nodo.
     */
    public int darAltura() {
        if (esHoja()) {
            return 1;
        } else {
            int a1 = (izq == null) ? 0 : izq.darAltura();
            int a2 = (der == null) ? 0 : der.darAltura();
            return 1 + Math.max(a1, a2);
        }
    }

    /**
     * Devuelve el número de contactos en el árbol que comienza en este nodo
     * utilizando un algoritmo iterativo.
     *
     * @return El número de contactos en el árbol que comienza en este nodo.
     */
    public int darPesoFrecuente() {
        int peso = 0;
        ArrayList pila = new ArrayList();
        Contacto p = this;
        while (p != null) {
            peso++;
            if (p.izq != null) {
                if (p.der != null) {
                    pila.add(p.der);
                }

                p = p.izq;
            } else if (p.der != null) {
                p = p.der;
            } else if (pila.size() != 0) {
                p = (Contacto) pila.get(0);
                pila.remove(0);
            } else {
                p = null;
            }
        }
        return peso;
    }

    /**
     * Devuelve el número de contactos en el árbol que comienza en este nodo.
     *
     * @return El número de contactos en el árbol que comienza en este nodo.
     */
    public int darPeso() {
        int p1 = (izq == null) ? 0 : izq.darPeso();
        int p2 = (der == null) ? 0 : der.darPeso();
        return 1 + p1 + p2;
    }

    /**
     * Devuelve el número de hojas en el árbol que comienza en este nodo.
     *
     * @return El número de hojas en el árbol que comienza en este nodo.
     */
    public int contarHojas() {
        if (esHoja()) {
            return 1;
        } else {
            int h1 = (izq == null) ? 0 : izq.contarHojas();
            int h2 = (der == null) ? 0 : der.contarHojas();
            return h1 + h2;
        }
    }

    /**
     * Inserta un nuevo contacto en el árbol que comienza en este nodo.
     *
     * @param nuevo El nuevo contacto que se va a insertar (debe ser diferente
     * de null).
     * @throws ContactoRepetidoException Se lanza esta excepción si el contacto
     * que se quiere agregar ya está en el directorio.
     */
    public void insertar(Contacto nuevo) throws ContactoRepetidoException {
        if (compareTo(nuevo) == 0) {
            throw new ContactoRepetidoException(nuevo.nombre);
        }

        if (compareTo(nuevo) > 0) {
            if (izq == null) {
                izq = nuevo;
            } else {
                izq.insertar(nuevo);
            }
        } else {
            if (der == null) {
                der = nuevo;
            } else {
                der.insertar(nuevo);
            }
        }
    }

    /**
     * Implementación iterativa para localizar un contacto en el árbol que
     * comienza en este nodo.
     *
     * @param unNombre Nombre que se va a buscar (debe ser diferente de null).
     * @return El contacto asociado al nombre. Si no lo encuentra, retorna null.
     */
    public Contacto buscarFrecuente(String unNombre) {
        Contacto p = this;
        while (p != null) {
            int comp = p.nombre.compareToIgnoreCase(unNombre);
            if (comp == 0) {
                return p;
            } else if (comp > 0) {
                p = p.izq;
            } else {
                p = p.der;
            }
        }
        return null;
    }

    /**
     * Implementación recursiva para localizar un contacto en el árbol que
     * comienza en este nodo.
     *
     * @param unNombre Nombre que se va a buscar (debe ser diferente de null).
     * @return El contacto asociado al nombre. Si no lo encuentra, retorna null.
     */
    public Contacto buscar(String unNombre) {
        if (nombre.compareToIgnoreCase(unNombre) == 0) {
            return this;
        } else if (nombre.compareToIgnoreCase(unNombre) > 0) {
            return (izq == null) ? null : izq.buscar(unNombre);
        } else {
            return (der == null) ? null : der.buscar(unNombre);
        }
    }

    /**
     * Elimina un contacto del árbol que comienza en este nodo.
     *
     * @param unNombre Nombre del contacto que se va a eliminar (debe haber un
     * contacto en el árbol con ese nombre).
     * @return El árbol de contactos después de eliminar el contacto indicado.
     */
    public Contacto eliminar(String unNombre) {
        if (esHoja()) {
            return null;
        }
        if (nombre.compareToIgnoreCase(unNombre) == 0) {
            if (izq == null) {
                return der;
            }
            if (der == null) {
                return izq;
            }
            Contacto sucesor = der.darMenor();
            der = der.eliminar(sucesor.getNombre());
            sucesor.izq = izq;
            sucesor.der = der;
            return sucesor;
        } else if (nombre.compareToIgnoreCase(unNombre) > 0) {
            izq = izq.eliminar(unNombre);
        } else {
            der = der.eliminar(unNombre);
        }
        return this;
    }

}
