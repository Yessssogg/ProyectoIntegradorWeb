
<%@page import="logica.Doctor"%>
<%@page import="logica.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Ver Doctores</h1>
    <p class="mb-4">A continuación podrá visualizar la lista completa de doctores.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Doctores</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Especialidad</th>
                            <th style="width:210px">Acción</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Especialidad</th>
                            <th style="width:210px">Acción</th>
                        </tr>
                    </tfoot>

                    <%
                        List<Doctor> listaDoctores = (List) request.getSession().getAttribute("listaDoctores");
                    %>

                    <tbody>
                        <% for (Doctor doctor : listaDoctores) {%>                                                                                                                                 
                        <tr>
                            <td id="id_doc<%= doctor.getId()%>"><%= doctor.getId()%></td>
                            <td><%= doctor.getNombre()%> <%= doctor.getApellido()%></td>
                            <td><%= doctor.getEspecialidad()%></td>


                            <td style="display: flex; width:230px;">
                                <form name="eliminar" action="SvEliminarDoctor" method="POST"> <!-- esto es para mandar el codigo al servlet -->
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red; margin-right:5px;">
                                        <i class="fas fa-trash-alt"></i>Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="<%= doctor.getId()%>"> <!-- esto es para mandar el codigo al servlet -->
                                </form>
                                <form name="editar" action="SvEditDoctor" method="GET">
                                    <button type="submit" class="btn btn-primary btn-user btn-block"; style="margin-left: 5px;">
                                        <i class="fas fa-pencil-alt"></i>Editar  
                                    </button>
                                    <input type="hidden" name="id" value="<%= doctor.getId()%>"> <!-- esto es para mandar el codigo al servlet -->
                                </form>
                            </td>
                        </tr> 
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->




<%@include file="components/bodyfinal.jsp"%>