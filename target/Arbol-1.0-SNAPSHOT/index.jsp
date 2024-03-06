<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.arbol.Contacto"%>
<%@include file= "templates/header.jsp" %>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<div class="container mt-5">
    <h2>DIRECTORIO</h2>

    <div class="input-group mb-3">
        <div class="form-outline" data-mdb-input-init>
            <input id="search-focus" type="search" id="form1" class="form-control" 
                   placeholder="Buscar por nombre" />
        </div>

    </div>   

    <%
        String alertType = (String) session.getAttribute("alertType");
        if (alertType != null) {
            if (alertType.equals("success")) {
    %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        Contacto agregado
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
    } else if (alertType.equals("danger")) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Ya existe un contacto con el nombre dado, Favor ingresa otro nombre.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
            }
            // Limpiar el atributo de la sesión después de mostrar la alerta
            session.removeAttribute("alertType");
        }
    %>




    <div class="table-responsive">
        <%
            ArrayList<Contacto> listaContactos = (ArrayList<Contacto>) session.getAttribute("listaContactos");
        %>
        <table id="contactosTable" class="table table-striped table-bordered ">
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellido</th>
                    <th scope="col">Dirección</th>
                    <th scope="col">Teléfono</th>
                    <th scope="col">Correo</th>
                    <th scope="col">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (listaContactos != null && !listaContactos.isEmpty()) {
                        for (Contacto contacto : listaContactos) {
                %>
                <tr id="<%= contacto.getNombre()%>">
                    <td><%= contacto.getIdContacto()%></td>
                    <td><%= contacto.getNombre()%></td>
                    <td><%= contacto.getApellido()%></td>
                    <td><%= contacto.getDireccion()%></td>
                    <td><%= contacto.getCelular()%></td>
                    <td><%= contacto.geteMail()%></td>
                    <td>


                        <!-- Icono de ver -->
                        <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalV"
                           data-nombre="<%= contacto.getNombre()%>"
                           data-apellido="<%= contacto.getApellido()%>"
                           data-direccion="<%= contacto.getDireccion()%>"
                           data-telefono="<%= contacto.getCelular()%>"
                           data-correo="<%= contacto.geteMail()%>">
                            <i class="fas fa-eye"></i> <!-- Reemplaza "fas fa-eye" con la clase del nuevo ícono de visualización -->
                        </a>

                        <!-- Icono de editar -->
                        <a href="#" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#editModal" title="Editar"
                           data-id="<%= contacto.getIdContacto()%>"
                           data-nombre="<%= contacto.getNombre()%>"
                           data-apellido="<%= contacto.getApellido()%>"
                           data-direccion="<%= contacto.getDireccion()%>"
                           data-telefono="<%= contacto.getCelular()%>"
                           data-correo="<%= contacto.geteMail()%>">
                            <i class="fas fa-pencil-alt"></i> <!-- Reemplaza "fas fa-pencil-alt" con la clase del nuevo ícono de edición -->
                        </a>

                        <!-- Icono de eliminar -->
                        <a href="#" title="Eliminar" class="btn btn-danger" onclick="confirmarEliminacion('<%= contacto.getNombre()%>')">
                            <i class="fas fa-trash-alt"></i> <!-- Reemplaza "fas fa-trash-alt" con la clase del nuevo ícono de eliminación -->
                        </a>

                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="7">No hay contactos disponibles</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <style>
            .btn-primary {
                background-color: #FFA500; /* Cambia el color de fondo del botón */
                color: #333; /* Cambia el color del texto dentro del botón */
            }
        </style>
        <div class="">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Agregar nuevo contacto
            </button>    
        </div>

    </div>
</div>

<!--- Script de filtrado de busqueda --->
<script>
    $(document).ready(function () {
        $('#search-focus').on('input', function () {
            var searchTerm = $(this).val().toLowerCase();

            $('tbody tr').each(function () {
                var nombre = $(this).find('td:eq(1)').text().toLowerCase();

                if (nombre.includes(searchTerm)) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
        });
    });
</script>


<%@include file= "templates/modales.jsp" %>
<%@include file= "templates/footer.jsp" %>