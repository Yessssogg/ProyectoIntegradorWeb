<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="components/bodyprimeraparte.jsp"%>

<div class="container-fluid">

    <h1 class="h3 mb-2 text-gray-800">Programar Turno</h1>
    <p class="mb-4">Complete el formulario para programar un nuevo turno. Use los filtros para encontrar al doctor y paciente adecuados.</p>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Formulario de Programación de Turno</h6>
        </div>
        <div class="card-body">
            <form action="SvTurno" method="post">
                <!-- Fecha del turno -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="fecha_turno" class="form-label">Fecha del Turno</label>
                        <input type="date" class="form-control" id="fecha_turno" name="fecha_turno" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="hora_turno" class="form-label">Hora del Turno</label>
                        <input type="time" class="form-control" id="hora_turno" name="hora_turno" required>
                    </div>
                </div>

                <!-- Seleccionar Especialidad -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="especialidad" class="form-label">Especialidad</label>
                        <select class="form-select" id="especialidad" name="especialidad" required onchange="filtrarDoctores()">
                            <option value="">Seleccione una especialidad</option>
                            <!-- Usar JSTL para iterar sobre las especialidades almacenadas en la sesión -->
                            <c:forEach var="especialidad" items="${sessionScope.listaEspecialidades}">
                                <option value="${especialidad}">${especialidad}</option>
                            </c:forEach>
                        </select>

                    </div>
                </div>

                <!-- Seleccionar Doctor -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="doctor" class="form-label">Doctor</label>
                        <select class="form-select" id="doctor" name="doctor" required onchange="mostrarHorarioDoctor()">
                            <option value="">Seleccione un doctor</option>
                            <!-- Se llenará dinámicamente con AJAX -->
                        </select>
                    </div>
                </div>

                <!-- Mostrar Horario del Doctor -->
                <div class="row">
                    <div class="form-group col-md-12">
                        <label for="horario_doctor" class="form-label">Horario del Doctor</label>
                        <textarea class="form-control" id="horario_doctor" name="horario_doctor" rows="3" readonly></textarea>
                    </div>
                </div>
                <!-- Campo para la Afección -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="afeccion" class="form-label">Afección</label>
                        <input type="text" class="form-control" id="afeccion" name="afeccion" placeholder="Escriba la afección" required>
                    </div>
                </div>
                <!-- Buscar validar PACIENTE -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="paciente" class="form-label">Buscar Paciente</label>
                        <input type="text" class="form-control" id="buscarPaciente" placeholder="Escriba el nombre o DNI del paciente" required>
                        <input type="hidden" id="dni" name="dni">
                        <div id="sugerenciasPacientes" class="list-group mt-2"></div>
                    </div>
                    <div class="col-md-6">
                        <!-- Botón para validar el DNI -->
                        <button type="button" class="btn btn-info mt-4" onclick="validarPaciente()">Validar Paciente</button>
                    </div>
                </div>

                <!-- Botón para registrar -->
                <button type="submit" class="btn btn-primary mt-3">Programar Turno</button>
            </form>
        </div>
    </div>

</div>

<%@include file="components/bodyfinal.jsp"%>
<script>
 function filtrarDoctores() {
    var especialidadId = document.getElementById('especialidad').value;

    if (especialidadId) {
        var url = 'SvDoctorPorEspecildad';

        var params = new URLSearchParams();
        params.append('especialidadId', especialidadId);

        fetch(url + '?' + params.toString(), {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())  // Convertimos la respuesta a JSON
        .then(data => {
            console.log("Doctores recibidos:", data);

            var doctorSelect = document.getElementById('doctor');
            doctorSelect.innerHTML = '<option value="">Seleccione un doctor</option>';

            // Llenar el select con los doctores recibidos
            data.forEach(doctor => {
                var option = document.createElement('option');
                option.value = doctor.id;  // El value del option será el ID del doctor
                option.textContent = doctor.nombre + ' ' + doctor.apellido;  // El texto visible será el nombre del doctor
                doctorSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('No se pudieron cargar los doctores.');
        });
    } else {
        alert('Por favor, seleccione una especialidad.');
    }
}





    // Mostrar el horario del doctor seleccionado
    function mostrarHorarioDoctor() {
        var doctorId = document.getElementById('doctor').value;
        if (doctorId) {
            // Hacer AJAX para obtener el horario del doctor seleccionado
            fetch('/getHorarioDoctor?doctorId=' + doctorId)
                    .then(response => response.json())
                    .then(data => {
                        document.getElementById('horario_doctor').value = data.horario; // Muestra el horario en el textarea
                    });
        }
    }
function validarPaciente() {
    var dni = document.getElementById('buscarPaciente').value;

    if (dni) {
        fetch('SvValidarPaciente', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'dni=' + encodeURIComponent(dni)
        })
        .then(response => response.text())
        .then(data => {
            // Si la validación es exitosa, asignamos el DNI al campo oculto
            document.getElementById('dni').value = dni;

            // Si es necesario, se puede mostrar una alerta o mensaje de éxito
            alert(data);
        })
        .catch(error => {
            alert('No existe el paciente.');
            console.error('Error:', error);
        });
    } else {
        alert('Por favor, ingrese un número de DNI.');
    }
}






</script>

<%@include file="components/bodyfinal.jsp"%>
