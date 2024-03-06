/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.arbol.Contacto;
import com.mycompany.arbol.ContactoRepetidoException;
import com.mycompany.arbol.Directorio;
import com.mycompany.arbol.Guardado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samir
 */
@WebServlet(name = "SvEditado", urlPatterns = {"/SvEditado"})
public class SvEditado extends HttpServlet {

    private ArrayList<Contacto> obtenerListaContactos(Directorio directorio) {
        return directorio.obtenerContactos();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el contexto del servlet
        ServletContext context = getServletContext();

        // Obtener el directorio del contexto
        Directorio directorio = (Directorio) context.getAttribute("directorio");

        if (directorio == null) {
            directorio = new Directorio();
            context.setAttribute("directorio", directorio);
        }

        // Data del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String celular = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("correo");
        String id = request.getParameter("id");

        // Verificar si el contacto existe
        Contacto existente = directorio.searchContact(nombre);
        if (existente != null) {
            // Si el contacto existe, realizar la edición
            existente.setIdContacto(Integer.parseInt(id)); // Actualizar el idContacto
            existente.setApellido(apellido);
            existente.setCelular(celular);
            existente.setDireccion(direccion);
            existente.seteMail(email);

            // Lógica para guardar los cambios, puedes ajustar según tus necesidades
            // Call guardarContacto to save the updated contact list
            Guardado.guardarContacto(directorio, context);

            // Load the updated contact list
            ArrayList<Contacto> listaContactos = obtenerListaContactos(directorio);

            // Set the updated contact list as a session attribute
            HttpSession session = request.getSession();
            session.setAttribute("listaContactos", listaContactos);

            // Set the success message as a session attribute
            session.setAttribute("successMessage", "Contacto editado exitosamente");

            // Redirect to the "index.jsp" page
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            // Si el contacto no existe, puedes manejar el caso como desees
            // Puedes mostrar un mensaje de error o realizar alguna acción específica
            // Aquí podrías redirigir a una página de error o mostrar un mensaje de error en la misma página
            response.sendRedirect(request.getContextPath() + "/index.jsp?errorMessage=Contacto no encontrado");
        }
    }

}
