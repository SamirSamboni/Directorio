/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol;

import java.io.Serializable;

/**
 *
 * @author
 */
public class ContactoRepetidoException extends Exception implements Serializable {

    public ContactoRepetidoException(String nombreContacto) {
        super("Ya existe un contacto con ese nombre: " + nombreContacto);
    }
}
