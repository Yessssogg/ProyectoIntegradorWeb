
package logica;

import java.util.Date;
import java.util.List;
import persistencia.ControladoraPersistencia;


public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    public void crearDoctor (String especialidad, String nombreUsuario, String contrasenia, String rol, 
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
    
    
    public void crearUsuario( String nombreUsuario, String contrasenia, String rol){
        Usuario usu = new Usuario ();
        usu.setNombreUsuario(nombreUsuario);
        usu.setContrasenia(contrasenia);
        usu.setRol(rol);
        controlPersis.crearUsuario(usu);
    }
    
    
    public List <Usuario> getUsuarios() {
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

    
    
}
