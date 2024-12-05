<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<div class="container-fluid">

    <h1 class="h3 mb-2 text-gray-800">Agregar Historia Clínica del Paciente</h1>
    <p class="mb-4">Complete el formulario para registrar una nueva historia clínica para el paciente. Asegúrese de ingresar toda la información relevante.</p>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Formulario de Registro de Historia Clínica</h6>
        </div>
        <div class="card-body">
            <form action="SvHistoria" method="POST">

                <!-- Datos de la historia clínica -->
                <h5>Datos de la Historia Clínica</h5>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="fechaCreacion">Fecha de Creación</label>
                        <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="alergias">Alergias</label>
                        <input type="text" class="form-control" id="alergias" name="alergias" placeholder="Ingrese alergias si tiene">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="enfermedadesPrevias">Enfermedades Previas</label>
                        <input type="text" class="form-control" id="enfermedadesPrevias" name="enfermedadesPrevias" placeholder="Ingrese enfermedades previas">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="tratamientosActuales">Tratamientos Actuales</label>
                        <input type="text" class="form-control" id="tratamientosActuales" name="tratamientosActuales" placeholder="Ingrese tratamientos actuales">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="cirugiasPrevias">Cirugías Previas</label>
                        <input type="text" class="form-control" id="cirugiasPrevias" name="cirugiasPrevias" placeholder="Ingrese cirugías previas">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="observaciones">Observaciones</label>
                        <input type="text" class="form-control" id="observaciones" name="observaciones" placeholder="Ingrese observaciones generales">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="estado">Estado</label>
                        <input type="text" class="form-control" id="estado" name="estado" placeholder="Ingrese el estado actual">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="proximaCita">Próxima Cita</label>
                        <input type="text" class="form-control" id="proximaCita" name="proximaCita">
                    </div>
                </div>

                <!-- Botón para registrar -->
                <button type="submit" class="btn btn-primary">Registrar Historia Clínica</button>
            </form>
        </div>
    </div>

</div>

<!-- /.container-fluid -->

<%@include file="components/bodyfinal.jsp"%>
