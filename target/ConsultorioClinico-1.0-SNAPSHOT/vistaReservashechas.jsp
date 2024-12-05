<%@page import="modelo.Turno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Reservas Hechas</h1>
    <p class="mb-4">A continuación podrá visualizar la lista completa de turnos reservados.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Turnos Reservados</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Doctor</th>
                            <th>Paciente</th>
                            <th>Afección</th>
                            <th style="width:300px">Acciones</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>ID</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Doctor</th>
                            <th>Paciente</th>
                            <th>Afección</th>
                            <th style="width:300px">Acciones</th>
                        </tr>
                    </tfoot>

                    <!-- Iteración sobre la lista de turnos -->
                    <tbody>
                        <c:forEach var="turno" items="${sessionScope.listaTurnos}">
                            <tr>
                                <td>${turno.id_turno}</td>
                                <td>${turno.fecha_turno}</td>
                                <td>${turno.doctor.nombre} ${turno.doctor.apellido}</td>
                                <td>${turno.pacien.nombre} ${turno.pacien.apellido}</td>
                                <td>${turno.afeccion}</td>
                                <td style="text-align: center;">
                                    <!-- Botón Ver Detalles -->
                                    <form action="SvDetallesTurno" method="GET" style="display: inline;">
                                        <input type="hidden" name="id" value="${turno.id_turno}">
                                        <button type="submit" class="btn btn-info btn-sm">
                                            <i class="fas fa-eye"></i> Ver Detalles
                                        </button>
                                    </form>
                                    <!-- Botón Eliminar -->
                                    <form action="SvEliminarTurno" method="POST" style="display: inline;">
                                        <input type="hidden" name="id" value="${turno.id_turno}">
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            <i class="fas fa-trash-alt"></i> Eliminar
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

<%@include file="components/bodyfinal.jsp"%>
