

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<h1>Hoola esto es una Prueba</h1>
<p>Esto es una prueba</p>
<form class="user">
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
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="fechanac" name="FechaNac"
                   placeholder="FechaNac">
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="telefono" name="Telefono"
                   placeholder="Telefono">
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="especialidad" name="Especialidad"
                   placeholder="Especialidad">
        </div>

        <!-- Aca va ir todo lo que respecta a horarios y usuarios  -->

    </div>


    <a href="#" class="btn btn-primary btn-user btn-block">
        Crear Odontologo
    </a>

</form>
<%@include file="components/bodyfinal.jsp"%>
