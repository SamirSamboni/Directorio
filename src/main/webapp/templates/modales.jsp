

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Agregar Nuevo contacto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body ">
                <form action="SvContacto" method="POST">
                    <div class="mb-3">
                        <label for="id" class="col-form-label">ID:</label>
                        <input type="text" class="form-control" id="id" name="id" placeholder="Ingresa su ID" required>
                    </div>
                    <div class="mb-3">
                        <label for="nombre" class="col-form-label">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresa su nombre" required>
                    </div>
                    <div class="mb-3">
                        <label for="apellido" class="col-form-label">Apellido:</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingresa su apellido" required>
                    </div>
                    <div class="mb-3">
                        <label for="direccion" class="col-form-label">Dirección:</label>
                        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Ingresa su direccion" required>
                    </div>
                    <div class="mb-3">
                        <label for="telefono" class="col-form-label">Teléfono:</label>
                        <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="Ingresa su número celular" maxlength="10" required pattern="[0-9]+" title="Solo se permiten números">
                    </div>
                    <div class="mb-3">
                        <label for="correo" class="col-form-label">Correo electrónico:</label>
                        <input type="email" class="form-control" id="correo" name="correo" placeholder="Ingresa su correo" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<div class="modal fade modal-dark" id="modalV" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content text-dark">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Detalles del contacto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="modal-body">
                    <div id="contacto-details">
                        <p><strong>Nombre: </strong><span id="modal-nombre"></span></p>
                        <p><strong>Apellido: </strong><span id="modal-apellido"></span></p>
                        <p><strong>Dirección: </strong><span id="modal-direccion"></span></p>
                        <p><strong>Teléfono: </strong><span id="modal-telefono"></span></p>
                        <p><strong>Correo: </strong><span id="modal-correo"></span></p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>


<script>
    $(document).ready(function () {
        $('#modalV').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var nombre = button.data('nombre');
            var apellido = button.data('apellido');
            var direccion = button.data('direccion');
            var telefono = button.data('telefono');
            var correo = button.data('correo');

            // Update the content of the span elements with the contact details
            $('#modal-nombre').text(nombre);
            $('#modal-apellido').text(apellido);
            $('#modal-direccion').text(direccion);
            $('#modal-telefono').text(telefono);
            $('#modal-correo').text(correo);
        });
    });
</script>

<div class="modal fade modal" id="editModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content  text-dark">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Edicion de contacto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="SvEditado" method="POST">
                    <div class="mb-3">
                        <label for="id" class="col-form-label">ID:</label>
                        <input type="text" class="form-control" id="id" name="id" placeholder="Ingresa su ID" required>
                    </div>
                    <div class="mb-3">
                        <label for="nombre" class="col-form-label">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresa su nombre" readonly required>
                    </div>
                    <div class="mb-3">
                        <label for="apellido" class="col-form-label">Apellido:</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingresa su apellido" required>
                    </div>
                    <div class="mb-3">
                        <label for="direccion" class="col-form-label">Dirección:</label>
                        <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Ingresa su direccion" required>
                    </div>
                    <div class="mb-3">
                        <label for="telefono" class="col-form-label">Teléfono:</label>
                        <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="Ingresa su número celular" maxlength="10" required pattern="[0-9]+" title="Solo se permiten números">
                    </div>
                    <div class="mb-3">
                        <label for="correo" class="col-form-label">Correo electrónico:</label>
                        <input type="email" class="form-control" id="correo" name="correo" placeholder="Ingresa su correo" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $('#editModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        var nombre = button.data('nombre');
        var apellido = button.data('apellido');
        var direccion = button.data('direccion');
        var telefono = button.data('telefono');
        var correo = button.data('correo');

        var modal = $(this);
        modal.find('#id').val(id);
        modal.find('#nombre').val(nombre);
        modal.find('#apellido').val(apellido);
        modal.find('#direccion').val(direccion);
        modal.find('#telefono').val(telefono);
        modal.find('#correo').val(correo);
    });
</script>





<script>
    function confirmarEliminacion(contactName) {
        if (confirm("¿Estás seguro de que deseas eliminar el contacto " + contactName + "?")) {
            $.ajax({
                type: "GET",
                url: "SvContacto",
                data: {action: "deleteContact", nombre: contactName},
                success: function (data) {
                    // Manejar éxito si es necesario
                    console.log("Contacto eliminado exitosamente");

                    // Actualizar la tabla en la página
                    $("#" + contactName).remove();
                },
                error: function (error) {
                    // Manejar error si es necesario
                    console.error("Error al eliminar el contacto", error);
                }
            });
        } else {
        }
    }
</script>