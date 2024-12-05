<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
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
                            <c:forEach var="especialidad" items="${listaEspecialidades}">
                                <option value="${especialidad.id}">${especialidad.nombre}</option>
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

                <!-- Buscar Paciente -->
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="paciente" class="form-label">Buscar Paciente</label>
                        <input type="text" class="form-control" id="buscarPaciente" placeholder="Escriba el nombre o DNI del paciente" required>
                        <input type="hidden" id="paciente" name="paciente">
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

<!-- Script de JavaScript -->
<script>
    // Filtrar Doctores según la especialidad seleccionada
    function filtrarDoctores() {
        var especialidadId = document.getElementById('especialidad').value;
        if (especialidadId) {
            // Hacer AJAX para obtener los doctores de la especialidad
            fetch('/getDoctoresByEspecialidad?especialidadId=' + especialidadId)
                .then(response => response.json())
                .then(data => {
                    var doctorSelect = document.getElementById('doctor');
                    doctorSelect.innerHTML = '<option value="">Seleccione un doctor</option>';
                    data.forEach(doctor => {
                        var option = document.createElement('option');
                        option.value = doctor.id;
                        option.textContent = doctor.nombre + ' ' + doctor.apellido;
                        doctorSelect.appendChild(option);
                    });
                });
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

    // Validar si el paciente existe en la base de datos por DNI
    function validarPaciente() {
        var dni = document.getElementById('buscarPaciente').value;
        if (dni) {
            // Realizar la validación del DNI
            fetch('/validarPacientePorDNI?dni=' + dni)
                .then(response => response.json())
                .then(data => {
                    if (data.existe) {
                        // Si el paciente existe, mostrar el mensaje de éxito
                        document.getElementById('paciente').value = data.id;
                        alert('Paciente encontrado: ' + data.nombre);
                    } else {
                        // Si el paciente no existe, mostrar un aviso
                        alert('El paciente no existe. Por favor, regístrelo en la historia.');
                    }
                })
                .catch(error => {
                    alert('Hubo un error al validar el paciente. Intente nuevamente.');
                });
        } else {
            alert('Por favor, ingrese un número de DNI.');
        }
    }

    // Función para buscar pacientes (en tiempo real, por nombre o DNI)
    function buscarPacientes() {
        var query = document.getElementById('buscarPaciente').value;
        if (query.length >= 3) {
            fetch('/buscarPaciente?q=' + query)
                .then(response => response.json())
                .then(data => {
                    var suggestions = document.getElementById('sugerenciasPacientes');
                    suggestions.innerHTML = '';
                    data.forEach(paciente => {
                        var item = document.createElement('div');
                        item.className = 'list-group-item list-group-item-action';
                        item.textContent = paciente.nombre + ' ' + paciente.apellido;
                        item.onclick = function() {
                            document.getElementById('paciente').value = paciente.id;
                            document.getElementById('buscarPaciente').value = paciente.nombre + ' ' + paciente.apellido;
                            suggestions.innerHTML = ''; // Limpiar las sugerencias
                        };
                        suggestions.appendChild(item);
                    });
                });
        }
    }
</script>

<%@include file="components/bodyfinal.jsp"%>
