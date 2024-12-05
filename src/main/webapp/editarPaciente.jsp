<%@page import="modelo.Paciente"%>
<%@page import="modelo.Responsable"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<h1>Edición de Pacientes</h1>
<p>Este es el apartado para modificar un paciente del sistema.</p>

<%
    Paciente paciente = (Paciente)request.getSession().getAttribute("pacienteEditar");
    Responsable responsable = paciente.getUnResponsable();
    String fechaFormateada = "";
    if (paciente.getFecha_nac() != null) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        fechaFormateada = sdf.format(paciente.getFecha_nac());
    }
%>

<form class="user" action="SvEditPaciente" method="post">
    <input type="hidden" name="id" value="<%= paciente.getId() %>">
    <div class="form-group row">
        <!-- Nombre -->
        <div class="col-sm-6 mb-3">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" value="<%= paciente.getNombre() %>">
        </div>
        <!-- Apellido -->
        <div class="col-sm-6 mb-3">
            <label for="apellido">Apellido</label>
            <input type="text" class="form-control form-control-user" id="apellido" name="apellido"
                   placeholder="Apellido" value="<%= paciente.getApellido() %>">
        </div>
        <!-- DNI -->
        <div class="col-sm-6 mb-3">
            <label for="dni">DNI</label>
            <input type="text" class="form-control form-control-user" id="dni" name="dni"
                   placeholder="DNI" value="<%= paciente.getDni() %>">
        </div>
        <!-- Teléfono -->
        <div class="col-sm-6 mb-3">
            <label for="telefono">Teléfono</label>
            <input type="text" class="form-control form-control-user" id="telefono" name="telefono"
                   placeholder="Teléfono" value="<%= paciente.getTelefono() %>">
        </div>
        <!-- Dirección -->
        <div class="col-sm-6 mb-3">
            <label for="direccion">Dirección</label>
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion"
                   placeholder="Dirección" value="<%= paciente.getDireccion() %>">
        </div>
        <!-- Fecha de Nacimiento -->
        <div class="col-sm-6 mb-3">
            <label for="fecha_nac">Fecha de Nacimiento</label>
            <input type="date" class="form-control form-control-user" id="fecha_nac" name="fecha_nac"
                   placeholder="Fecha de Nacimiento" value="<%= fechaFormateada %>">
        </div>
        <!-- Tipo de Sangre -->
        <div class="col-sm-6 mb-3">
            <label for="tipo_sangre">Tipo de Sangre</label>
            <input type="text" class="form-control form-control-user" id="tipo_sangre" name="tipo_sangre"
                   placeholder="Tipo de Sangre" value="<%= paciente.getTipo_Sangre() %>">
        </div>
        <!-- Tiene Obra Social -->
        <div class="col-sm-6 mb-3">
            <label for="tiene_os">¿Tiene Obra Social?</label>
            <select class="form-control" id="tiene_os" name="tiene_os">
                <option value="true" <%= paciente.isTiene_OS() ? "selected" : "" %>>Sí</option>
                <option value="false" <%= !paciente.isTiene_OS() ? "selected" : "" %>>No</option>
            </select>
        </div>
    </div>
    <!-- Botón de Guardar -->
    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar Modificación
    </button>
</form>

<%@include file="components/bodyfinal.jsp"%>
