package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import controlador.ControladoraPersistencia;
import java.time.LocalDate;

public class Controladora { 
 ControladoraPersistencia controlPersis = new ControladoraPersistencia();
 
 public Turno crearTurno( Date fechaTurno, String horaTurno, String afeccion, int doctorId, String pacienteDni) {
    try {
        Doctor doctor = controlPersis.buscarDoctor(doctorId);
        if (doctor == null) {
            System.err.println("Error: Doctor con ID " + doctorId + " no encontrado.");
            return null; // Salir si no se encuentra el doctor
        }
        Paciente paciente = controlPersis.buscarPacientePorSuDNI(pacienteDni);
        if (paciente == null) {
            System.err.println("Error: Paciente con DNI " + pacienteDni + " no encontrado.");
            return null; // Salir si no se encuentra el paciente
        }
        Turno turno = new Turno();
        turno.setFecha_turno(fechaTurno);   // Fecha del turno
        turno.setHora_turno(horaTurno);    // Hora del turno
        turno.setAfeccion(afeccion);       // Afección o especialidad
        turno.setDoctor(doctor);          // Asignar el doctor
        turno.setPacien(paciente);        // Asignar el paciente
        // Guardar el turno en la base de datos
        controlPersis.guardarTurno(turno);
        // Devolver el turno creado
        return turno;
    } catch (Exception e) {
        e.printStackTrace();
        return null; // Retornar null en caso de error
    }
}
  public Paciente buscarPacientePorDNI(String dni) {
        return controlPersis.buscarPacientePorSuDNI(dni);
    }  
  public Historia crearHistoria(String fechaCreacion, String alergias, String enfermedadesPrevias, 
            String tratamientosActuales, String cirugiasPrevias, String observaciones, 
            String estado, String proximaCita, List<Detalles> detallesAtencion) {
        // Crear el objeto Historia
        Historia historia = new Historia();
        // Asignar los valores a la historia
        if (fechaCreacion != null && !fechaCreacion.isEmpty()) {
            historia.setFechaCreacion(LocalDate.parse(fechaCreacion));
        }
        historia.setAlergias(alergias);
        historia.setEnfermedadesPrevias(enfermedadesPrevias);
        historia.setTratamientosActuales(tratamientosActuales);
        historia.setCirugiasPrevias(cirugiasPrevias);
        historia.setObservaciones(observaciones);
        historia.setEstado(estado);
        historia.setProximaCita(proximaCita);
        historia.setDetallesAtencion((List<Detalles>) (detallesAtencion != null ? detallesAtencion : new ArrayList<>()));
        // Llamar a la persistencia para guardar la historia
        return controlPersis.crearHistoria(historia);
    }
public void crearPaciente(boolean tieneOS, String tipoSangre, Responsable unResponsable, 
                          Historia historia, String dni, String nombre, String apellido, 
                          String telefono, String direccion, Date fechaNac) {
    Paciente paciente = new Paciente();
    paciente.setDni(dni);
    paciente.setNombre(nombre);
    paciente.setApellido(apellido);
    paciente.setTelefono(telefono);
    paciente.setDireccion(direccion);
    paciente.setFecha_nac(fechaNac);  
    paciente.setTiene_OS(tieneOS);
    paciente.setTipo_Sangre(tipoSangre);
    paciente.setUnResponsable(unResponsable); 
    Historia historiaExistente = controlPersis.tarerHistoria(historia.getId_historia()); // Buscar por ID
    paciente.setHistoria(historiaExistente);
    controlPersis.crearPaciente(paciente);
}
public Responsable crearResponsable(String tipoResp, String dni, String nombre, String apellido, 
                                     String telefono, String direccion, Date fechaNac) {
    Responsable responsable = new Responsable();   
    responsable.setDni(dni);
    responsable.setNombre(nombre);
    responsable.setApellido(apellido);
    responsable.setTelefono(telefono);
    responsable.setDireccion(direccion);
    responsable.setFecha_nac(fechaNac);  
    responsable.setTipo_resp(tipoResp); 
    controlPersis.crearResponsable(responsable);  
    return responsable;
}
   public Responsable buscarResponsable(int responsableId) {
        return controlPersis.tarerResponsable(responsableId);
    }   
  public Historia buscarHistoria(int historiaId) {
        return controlPersis.tarerHistoria(historiaId);
    }    
    public Doctor buscarDoctor(int idDoctor) {
        return controlPersis.buscarDoctor(idDoctor);
    } 
    public Paciente buscarPaciente(int idPaciente) {
       return controlPersis.buscarPaciente(idPaciente);
    }    
    public void editarDoctor(Doctor doctor) {
        controlPersis.editarDoctor(doctor);
    }
    public void editarHorario(Horario horario) {
        controlPersis.editarHorario(horario);
    }   
    public void crearDoctor(String especialidad, String nombreUsuario, String contrasenia, String rol,
            String dni, String nombre, String apellido, String telefono,
            String direccion, Date fecha_nac, String horarioInicio, String horarioFin) {   
        Usuario usu = new Usuario();
        usu.setNombreUsuario(nombreUsuario);
        usu.setContrasenia(contrasenia);
        usu.setRol(rol);
        Horario horario = new Horario();
        horario.setHorario_inicio(horarioInicio);
        horario.setHorario_fin(horarioFin);
        // Crear el Doctor y asociar el Usuario y Horario creados
        Doctor doctor = new Doctor();
        doctor.setEspecialidad(especialidad);
        doctor.setUnUsuario(usu);
        doctor.setUnHorario(horario);
        doctor.setDni(dni);
        doctor.setNombre(nombre);
        doctor.setApellido(apellido);
        doctor.setTelefono(telefono);
        doctor.setDireccion(direccion);
        doctor.setFecha_nac(fecha_nac);
        controlPersis.crearUsuario(usu);  
        controlPersis.crearHorario(horario); 
        controlPersis.crearDoctor(doctor);  
    }
    public void crearUsuario(String nombreUsuario, String contrasenia, String rol) {
        Usuario usu = new Usuario();
        usu.setNombreUsuario(nombreUsuario);
        usu.setContrasenia(contrasenia);
        usu.setRol(rol);
        controlPersis.crearUsuario(usu);
    }
    public List<Usuario> getUsuarios() {
        return controlPersis.getUsuarios();
    }
    public void borrarUsuario(int id) throws Exception {
        Usuario usuario = controlPersis.tarerUsuario(id);
        if (usuario != null) {
            Doctor doctor = controlPersis.buscarDoctorPorUsuario(id);
            if (doctor != null) {
                controlPersis.borrarDoctor(doctor.getId());
            }
            controlPersis.borrarUsuario(id);
        }
    }
    public Usuario traerUsuario(int id) {
        return controlPersis.tarerUsuario(id);
    }
    public void editarUsuario(Usuario usu) {
        controlPersis.editarUsuario(usu);
    }
    public List<Paciente> getPacientes() {
        return controlPersis.getPaciente();
    } 
        public List<Doctor> obtenerDoctoresPorEspecialidad(String especialidad) {
        return controlPersis.getDoctoresPorEspecialidad(especialidad);
    }   
    public List<Doctor> getDoctores() {
        return controlPersis.getDoctores();
    }
    public void borrarDoctor(int id) throws Exception {
        try {
            Doctor doctor = controlPersis.buscarDoctor(id);
            if (doctor != null) {
                Horario horario = doctor.getUnHorario();
                if (horario != null) {
                    doctor.setUnHorario(null);  
                    controlPersis.editarDoctor(doctor); 
                    // Ahora puedes eliminar el Horario
                    controlPersis.borrarHorario(horario.getId_horario());
                }
                 controlPersis.borrarTurnoporIdDoctor(id);
                // Finalmente, elimina el Doctor
                controlPersis.borrarDoctor(id);
            }
        } catch (Exception ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error al borrar el doctor", ex);
        }
    }   
    public boolean comprobarIngreso(String usuario, String contrasenia) {
       boolean ingreso = false;       
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        listaUsuarios = controlPersis.getUsuarios();
        
        for (Usuario usu : listaUsuarios) {
            if(usu.getNombreUsuario().equals(usuario)) {
                if (usu.getContrasenia().equals(contrasenia)) {
                    ingreso = true; 
                }
                else {
                    ingreso = false;
                }            
            }
        }
       return ingreso; 
    }  

    public void editarPaciente(Paciente paciente) {
       controlPersis.editarPaciente(paciente);
    }

    public void borrarPaciente(int idPaciente) throws Exception {
    try {
        Paciente paciente = controlPersis.buscarPaciente(idPaciente);
        if (paciente != null) {
            Historia historia = paciente.getHistoria();
            if (historia != null) {
                List<Detalles> detalles = historia.getDetallesAtencion();
                if (detalles != null) {
                    for (Detalles detalle : detalles) {
                        controlPersis.borrarDetalleAtencion(detalle.getId_detalles());
                    }
                }       
            }

            // Eliminar los turnos asociados al paciente
            List<Turno> turnos = paciente.getListaTurnos();
            if (turnos != null) {
                for (Turno turno : turnos) {
                    controlPersis.borrarTurno(turno.getId_turno());
                }
            }
            controlPersis.borrarPaciente(idPaciente);
            Responsable responsable = paciente.getUnResponsable();
            if (responsable != null) {
                controlPersis.borrarResponsable(responsable.getId());
            }
                controlPersis.borrarHistoriaClinica(historia.getId_historia());
        }
    } catch (Exception ex) {
        Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        throw new Exception("Error al borrar el paciente", ex);
    }
}

   
    


   
    

   

    
    

    

}
