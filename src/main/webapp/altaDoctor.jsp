

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<h1>Registra Doctor</h1>
<p>Registra a un nuevo especialista</p>
<form class="user" action="SvDoctor" method="post">
    <div class="form-group row">
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="dni" name="dni"
                   placeholder="DNI">
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="nombre" name="Nombre"
                   placeholder="Nombre">
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="apellido" name="Apellido"
                   placeholder="Apellido">
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="telefono" name="Telefono"
                   placeholder="Telefono">
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="direccion" name="Direccion"
                   placeholder="Direccion">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha_nac" name="fecha_nac" placeholder="Fecha de Nacimiento">
        </div>     
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="especialidad" name="Especialidad"
                   placeholder="Especialidad">
        </div>
        <!-- Aca van los campos para Usuario y Horario -->
        <!-- Usuario -->
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="usuario" name="Usuario" placeholder="Usuario" >
        </div>
        <div class="col-sm-6 mb-3">
            <input type="password" class="form-control form-control-user" id="contrasena" name="Contrasena" placeholder="Contraseña" >
        </div>

        <!-- Horario -->
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="horario_inicio" name="Horario_inicio"  placeholder="Horario de Inicio" >

        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="horario_fin" name="Horario_fin"  placeholder="Horario de Fin"  >
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Crear Doctor
    </button>

</form>
<%@include file="components/bodyfinal.jsp"%>
