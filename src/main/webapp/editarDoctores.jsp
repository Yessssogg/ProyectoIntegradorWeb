<%@page import="logica.Doctor"%>
<%@page import="logica.Horario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<h1>Edición de Doctores</h1>
<p>Este es el apartado para modificar a un doctor del sistema.</p>

<% 
    Doctor doctor = (Doctor)request.getSession().getAttribute("doctorEditar");
    Horario horario = doctor.getUnHorario();
%>

<form class="user" action="SvEditDoctor" method="post">
    <input type="hidden" name="id" value="<%= doctor.getId() %>">
    <div class="form-group row">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" value="<%= doctor.getNombre() %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="apellido" name="apellido"
                   placeholder="Apellido" value="<%= doctor.getApellido() %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="especialidad" name="especialidad"
                   placeholder="Especialidad" value="<%= doctor.getEspecialidad() %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="dni" name="dni"
                   placeholder="DNI" value="<%= doctor.getDni() %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="telefono" name="telefono"
                   placeholder="Teléfono" value="<%= doctor.getTelefono() %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion"
                   placeholder="Dirección" value="<%= doctor.getDireccion() %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha_nac" name="fecha_nac"
                   placeholder="Fecha de Nacimiento" value="<%= doctor.getFecha_nac() %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="horario_inicio" name="horario_inicio"
                   placeholder="Horario Inicio" value="<%= (horario != null) ? horario.getHorario_inicio() : "" %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="horario_fin" name="horario_fin"
                   placeholder="Horario Fin" value="<%= (horario != null) ? horario.getHorario_fin() : "" %>">
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar Modificación
    </button>
</form>

<%@include file="components/bodyfinal.jsp"%>
