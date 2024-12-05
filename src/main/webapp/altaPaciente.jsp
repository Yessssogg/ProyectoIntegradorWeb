<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">
    <h1 class="h3 mb-2 text-gray-800">Detalles de la Historia Clínica</h1>
    <p class="mb-4">A continuación, se muestran los detalles de la historia clínica registrada:</p>  

    <!-- Usando JSTL para acceder al objeto de historia clínica -->
    <c:set var="historia" value="${sessionScope.historiaEditar}" />

    <!-- Formulario para actualizar o procesar datos relacionados con la historia clínica -->
    <form action="SvPaciente" method="POST">
        <!-- Campo oculto para vincular -->
        <input type="hidden" name="historiaId" value="${historia.id_historia}" />

        <!-- Mostrar los detalles de la historia clínica -->
        <div class="form-group row">
            <div class="col-sm-6 mb-3">
                <label><strong>Fecha de Creación:</strong></label>
                <p class="form-control-static">${historia.fechaCreacion}</p>
            </div>
            <div class="col-sm-6 mb-3">
                <label><strong>Alergias:</strong></label>
                <p class="form-control-static">${historia.alergias}</p>
            </div>
            <div class="col-sm-6 mb-3">
                <label><strong>Enfermedades Previas:</strong></label>
                <p class="form-control-static">${historia.enfermedadesPrevias}</p>
            </div>
            <div class="col-sm-6 mb-3">
                <label><strong>Tratamientos Actuales:</strong></label>
                <p class="form-control-static">${historia.tratamientosActuales}</p>
            </div>
            <div class="col-sm-6 mb-3">
                <label><strong>Cirugías Previas:</strong></label>
                <p class="form-control-static">${historia.cirugiasPrevias}</p>
            </div>
            <div class="col-sm-6 mb-3">
                <label><strong>Observaciones:</strong></label>
                <p class="form-control-static">${historia.observaciones}</p>
            </div>
            <div class="col-sm-6 mb-3">
                <label><strong>Estado:</strong></label>
                <p class="form-control-static">${historia.estado}</p>
            </div>
            <div class="col-sm-6 mb-3">
                <label><strong>Próxima Cita:</strong></label>
                <p class="form-control-static">${historia.proximaCita}</p>
            </div>
        </div>

        <!-- Page Heading -->
        <h1 class="h3 mb-2 text-gray-800">Agregar Paciente</h1>
        <p class="mb-4">Complete el formulario para registrar un nuevo paciente. Si el paciente es menor de 18 años, deberá registrar a un responsable.</p>

        <!-- Formulario de registro -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Formulario de Registro</h6>
            </div>
            <div class="card-body">
                <form action="SvPaciente" method="POST">
                    <input type="hidden" name="historiaId" value="${historiaId}" />

                    <!-- Datos del paciente -->
                    <h5>Datos del Paciente</h5>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="nombre">Nombre del Paciente</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="apellido">Apellido del Paciente</label>
                            <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Ingrese el apellido" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="dni">DNI</label>
                            <input type="text" class="form-control" id="dni" name="dni" maxlength="8" placeholder="Ingrese el DNI (8 dígitos)" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="fechaNacimiento">Fecha de Nacimiento</label>
                            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="telefono">Teléfono</label>
                            <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Ingrese el teléfono" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="direccion">Dirección</label>
                            <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Ingrese la dirección" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="tipoSangre">Tipo de Sangre</label>
                            <input type="text" class="form-control" id="tipoSangre" name="tipoSangre" placeholder="Ingrese el tipo de sangre">
                        </div>
                        <div class="form-group col-md-6 form-check">
                            <input type="checkbox" class="form-check-input" id="tieneOS" name="tieneOS">
                            <label class="form-check-label" for="tieneOS">¿Tiene Obra Social?</label>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">Registrar Paciente</button>

                    <div class="form-group form-check form-switch">
                        <input class="form-check-input" type="checkbox" id="esMayorDeEdad" name="esMayorDeEdad" onchange="toggleResponsable()">
                        <label class="form-check-label" for="esMayorDeEdad">¿Es mayor de 18 años?</label>
                    </div>

                    <!-- Sección del responsable -->
                    <div id="responsableSection" style="display:block; margin-top: 20px;">
                        <h5>Datos del Responsable</h5>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label for="nombreResponsable">Nombre del Responsable</label>
                                <input type="text" class="form-control" id="nombreResponsable" name="nombreResponsable" placeholder="Ingrese el nombre">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="apellidoResponsable">Apellido del Responsable</label>
                                <input type="text" class="form-control" id="apellidoResponsable" name="apellidoResponsable" placeholder="Ingrese el apellido">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label for="dniResponsable">DNI del Responsable</label>
                                <input type="text" class="form-control" id="dniResponsable" name="dniResponsable" maxlength="8" placeholder="Ingrese el DNI (8 dígitos)">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="telefonoResponsable">Teléfono del Responsable</label>
                                <input type="text" class="form-control" id="telefonoResponsable" name="telefonoResponsable" placeholder="Ingrese el teléfono">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label for="fechaNacimiento">Fecha de Nacimiento</label>
                                <input type="date" class="form-control" id="fechaNacimientoResponsable" name="fechaNacimientoResponsable" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="direccionResponsable">Dirección del Responsable</label>
                                <input type="text" class="form-control" id="direccionResponsable" name="direccionResponsable" placeholder="Ingrese la dirección">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </form>
</div>
<!-- /.container-fluid -->

<%@include file="components/bodyfinal.jsp"%>

<script>
    function toggleResponsable() {
    const esMayorDeEdad = document.getElementById("esMayorDeEdad").checked;
    const responsableSection = document.getElementById("responsableSection");
    const inputsResponsable = responsableSection.querySelectorAll("input");

    if (esMayorDeEdad) {
        responsableSection.style.display = "none";
        inputsResponsable.forEach(input => {
            input.required = false; // Elimina las restricciones
            input.disabled = true; // Desactiva los campos
        });
    } else {
        responsableSection.style.display = "block";
        inputsResponsable.forEach(input => {
            input.required = true; // Activa las restricciones
            input.disabled = false; // Habilita los campos
        });
    }
}

// Asegúrate de ejecutar la función al cargar la página
document.addEventListener("DOMContentLoaded", toggleResponsable);

</script>
