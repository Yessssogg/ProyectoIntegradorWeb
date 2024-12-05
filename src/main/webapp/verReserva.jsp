<%@page import="modelo.Paciente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Ver Pacientes</h1>
    <p class="mb-4">A continuación podrá visualizar la lista completa de pacientes.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Pacientes</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Apellido</th>
                            <th>Nombre</th>
                            <th>DNI</th>
                            <th>Teléfono</th>
                            <th>ID Historia</th> <!-- Add this column -->
                            <th style="width:300px">Acciones</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>ID</th>
                            <th>Apellido</th>
                            <th>Nombre</th>
                            <th>DNI</th>
                            <th>Teléfono</th>
                            <th>ID Historia</th> <!-- Add this column -->
                            <th style="width:300px">Acciones</th>
                        </tr>
                    </tfoot>

                    <!-- Iteración sobre la lista de pacientes -->
                    <tbody>
                        <c:forEach var="paciente" items="${sessionScope.listaPacientes}">
                            <tr>
                                <td>${paciente.id}</td>
                                <td>${paciente.apellido}</td>
                                <td>${paciente.nombre}</td>
                                <td>${paciente.dni}</td>
                                <td>${paciente.telefono}</td>
                                <td>${paciente.historia.id_historia}</td> <!-- Display the Historia ID -->
                                <td style="text-align: center;">
                                    <!-- Botón Ver Detalles -->
                                    <form action="SvDetallesPaciente" method="GET" style="display: inline;">
                                        <input type="hidden" name="id" value="${paciente.id}">
                                        <button type="submit" class="btn btn-info btn-sm">
                                            <i class="fas fa-eye"></i> Ver Detalles
                                        </button>
                                    </form>
                                    <!-- Botón Editar -->
                                    <form action="SvEditPaciente" method="GET" style="display: inline;">
                                        <input type="hidden" name="id" value="${paciente.id}">
                                        <button type="submit" class="btn btn-primary btn-sm">
                                            <i class="fas fa-pencil-alt"></i> Editar  
                                        </button>
                                    </form>

                                    <!-- Botón Eliminar -->
                                    <form action="SvEliminarPaciente" method="POST" style="display: inline;">
                                        <input type="hidden" name="id" value="${paciente.id}">
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
