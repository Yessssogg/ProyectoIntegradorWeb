
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Doctor;
import logica.Horario;
import logica.Usuario;
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
        // Encuentra el doctor que quieres eliminar
        Doctor doctor = doctorJPA.findDoctor(id);
        if (doctor != null) {
            // Desvincula el Horario del Doctor
            Horario horario = doctor.getUnHorario();
            if (horario != null) {
                doctor.setUnHorario(null);  // Desvincula el horario del doctor
                doctorJPA.edit(doctor);     // Guarda los cambios
                
                // Ahora puedes eliminar el Horario
                horaJPA.destroy(horario.getId_horario());
            }

            // Elimina el Doctor
            doctorJPA.destroy(id);
        }
    } catch (NonexistentEntityException ex) {
        Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public List<Doctor> getDoctores() {
       return doctorJPA.findDoctorEntities();
    }


}
