package controlador;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Doctor;
import modelo.Historia;
import modelo.Horario;
import modelo.Paciente;

import modelo.Responsable;
import modelo.Turno;
import modelo.Usuario;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    HorarioJpaController horaJPA = new HorarioJpaController();
    DoctorJpaController doctorJPA = new DoctorJpaController();
    PacienteJpaController pacJPA = new PacienteJpaController();
    PersonaJpaController persJPA = new PersonaJpaController();
    ResponsableJpaController respJPA = new ResponsableJpaController();
    SecretarioJpaController secreJPA = new SecretarioJpaController();
    TurnoJpaController turnJPA = new TurnoJpaController();
    UsuarioJpaController usuJPA = new UsuarioJpaController();
    DetallesJpaController detallesJPA = new DetallesJpaController();
    HistoriaJpaController historiaJPA = new HistoriaJpaController();

    public void borrarTurnoporIdDoctor(int id) {
        Doctor doctor = doctorJPA.findDoctor(id);  // Método findDoctor que busca al doctor por ID

        if (doctor != null) {
            // Obtener la lista de turnos asociados al doctor
            List<Turno> turnos = doctor.getListaTurnos();
            // Verificar si existen turnos y eliminarlos
            if (turnos != null && !turnos.isEmpty()) {
                for (Turno turno : turnos) {
                    try {
                        // Eliminar cada turno asociado
                        turnJPA.destroy(turno.getId_turno());
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            try {
                // Eliminar el doctor
                doctorJPA.destroy(doctor.getId());  // Método destroy de DoctorJpaController que elimina el doctor
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Si no se encuentra el doctor, lanzar una excepción o manejar el error
            System.out.println("Doctor no encontrado con ID: " + id);
        }
    }

    public void guardarTurno(Turno turno) {
        try {
            turnJPA.create(turno);  // Usar el JPA controller de Doctor para persistir
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Doctor> getDoctoresPorEspecialidad(String especialidad) {
        return doctorJPA.findDoctoresByEspecialidad(especialidad);
    }

    public Paciente buscarPacientePorSuDNI(String dni) {
        try {
            // Usar el método findPacienteEntities o un método específico para consultar por DNI
            List<Paciente> pacientes = pacJPA.findPacienteEntities();

            // Filtrar los pacientes para encontrar el que coincide con el DNI
            for (Paciente paciente : pacientes) {
                if (paciente.getDni().equals(dni)) {
                    return paciente; // Retorna el paciente encontrado
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registrar errores si los hay
        }

        // Si no encuentra un paciente con ese DNI, devuelve null
        return null;
    }

    public Historia crearHistoria(Historia historia) {
        // Guardar el objeto Historia en la base de datos
        historiaJPA.create(historia);  // Usamos el JPA para guardar la historia
        return historia;  // Retornamos el objeto Historia que ahora está guardado
    }

    public void crearResponsable(Responsable resp) {
        respJPA.create(resp);
    }

    public void crearPaciente(Paciente paci) {
        try {
            // Verificar que el paciente tenga una historia clínica
            if (paci.getHistoria() == null) {
                throw new IllegalArgumentException("El paciente debe tener una historia clínica.");
            }

            // Persistir el paciente
            pacJPA.create(paci);  // Guardamos el paciente en la base de datos
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "Error al crear el paciente", ex);
            throw new RuntimeException("No se pudo crear el paciente: " + ex.getMessage(), ex);
        }
    }

    public Historia tarerHistoria(int id) {
        return historiaJPA.findHistoria(id);
    }

    public void crearUsuario(Usuario usu) {
        usuJPA.create(usu);
    }

    public List<Usuario> getUsuarios() {
        return usuJPA.findUsuarioEntities();
    }

    public void borrarUsuario(int id) {
        try {
            usuJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario tarerUsuario(int id) {
        return usuJPA.findUsuario(id);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJPA.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void crearHorario(Horario horario) {
        try {
            horaJPA.create(horario);  // Usar el JPA controller de Horario para persistir
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void crearDoctor(Doctor doctor) {
        try {
            doctorJPA.create(doctor);  // Usar el JPA controller de Doctor para persistir
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Doctor buscarDoctorPorUsuario(int usuarioId) {
        Usuario usuario = usuJPA.findUsuario(usuarioId);
        if (usuario != null) {
            return doctorJPA.findDoctorByUsuario(usuario);
        }
        return null;
    }
    public void borrarDoctor(int id) throws Exception {
        try {
            Doctor doctor = doctorJPA.findDoctor(id);
            if (doctor != null) {
                Horario horario = doctor.getUnHorario();
                if (horario != null) {
                    doctor.setUnHorario(null);  
                    doctorJPA.edit(doctor);    
                    horaJPA.destroy(horario.getId_horario());
                }
                doctorJPA.destroy(id);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Paciente> getPaciente() {
        return pacJPA.findPacienteEntities();
    }
    public List<Doctor> getDoctores() {
        return doctorJPA.findDoctorEntities();
    }
    public Doctor buscarDoctor(int id) {
        return doctorJPA.findDoctor(id);  // Usar el método del JPA controller
    }

    public Paciente buscarPaciente(int idPaciente) {
        return pacJPA.findPaciente(idPaciente);
    }
    public void editarDoctor(Doctor doctor) {
        try {
            doctorJPA.edit(doctor);  // Actualiza el doctor en la base de datos
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void borrarHorario(int id_horario) {
        try {
            horaJPA.destroy(id_horario);  // Elimina el horario usando el JPA controller
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editarHorario(Horario horario) {
        try {
            horaJPA.edit(horario); // Usar el JPA controller para persistir cambios
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error al editar el horario", ex);
        }
    }
    public Responsable tarerResponsable(int responsableId) {
        return respJPA.findResponsable(responsableId);
    }
    public void editarPaciente(Paciente paciente) {
        try {
            pacJPA.edit(paciente);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void borrarDetalleAtencion(int id_detalles) {
        try {
            detallesJPA.destroy(id_detalles);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarHistoriaClinica(int id_historia) {
        try {
            historiaJPA.destroy(id_historia);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarResponsable(int id) {
        try {
            respJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarTurno(int id_turno) {
        try {
            turnJPA.destroy(id_turno);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarPaciente(int idPaciente) {
        try {
            pacJPA.destroy(idPaciente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
