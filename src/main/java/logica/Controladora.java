package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import persistencia.ControladoraPersistencia;

public class Controladora {

  
 ControladoraPersistencia controlPersis = new ControladoraPersistencia();
 

    public Doctor buscarDoctor(int idDoctor) {
        return controlPersis.buscarDoctor(idDoctor);
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

        // Crear Usuario asociado al Doctor     
        Usuario usu = new Usuario();
        usu.setNombreUsuario(nombreUsuario);
        usu.setContrasenia(contrasenia);
        usu.setRol(rol);

        // Crear Horario asociado al Doctor
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

        // Guardar el Usuario, Horario y Doctor en la base de datos
        controlPersis.crearUsuario(usu);   // Guardar el usuario primero
        controlPersis.crearHorario(horario); // Guardar el horario
        controlPersis.crearDoctor(doctor);  // Finalmente guardar el docto

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

                // Primero elimina el doctor si existe
                controlPersis.borrarDoctor(doctor.getId());
            }
            // Ahora puedes eliminar el usuario
            controlPersis.borrarUsuario(id);
        }

    }

    public Usuario traerUsuario(int id) {
        return controlPersis.tarerUsuario(id);
    }

    public void editarUsuario(Usuario usu) {
        controlPersis.editarUsuario(usu);
    }

    public List<Doctor> getDoctores() {
        return controlPersis.getDoctores();
    }

    public void borrarDoctor(int id) throws Exception {
        try {
            // Encuentra el doctor que quieres eliminar
            Doctor doctor = controlPersis.buscarDoctor(id);
            if (doctor != null) {
                // Desvincula y elimina el Horario del Doctor
                Horario horario = doctor.getUnHorario();
                if (horario != null) {
                    doctor.setUnHorario(null);  // Desvincula el horario
                    controlPersis.editarDoctor(doctor);  // Actualiza el doctor sin horario

                    // Ahora puedes eliminar el Horario
                    controlPersis.borrarHorario(horario.getId_horario());
                }

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
    

}
