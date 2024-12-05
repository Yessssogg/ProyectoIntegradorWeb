
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Controladora;
import modelo.Historia;
import modelo.Paciente;
import modelo.Responsable;


@WebServlet(name = "SvPaciente", urlPatterns = {"/SvPaciente"})
public class SvPaciente extends HttpServlet {

     Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Paciente> listaPacientes = new ArrayList<Paciente>();
        listaPacientes = control.getPacientes();
        HttpSession misession = request.getSession();
        misession.setAttribute("listaPacientes", listaPacientes);
        response.sendRedirect("verPacientes.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        try {
            // Recoger los parámetros del formulario (asegúrate de que los nombres coincidan con los del formulario)
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String tipoSangre = request.getParameter("tipoSangre");
            boolean tieneOS = Boolean.parseBoolean(request.getParameter("tieneOS"));
            String responsableId = request.getParameter("responsableId"); // Si es opcional
            String historiaId = request.getParameter("historiaId"); // ID de la historia clínica
              String fechaNacimientoStr  = request.getParameter("fechaNacimiento");
        // Validar y convertir la fecha de nacimiento
        Date fechaNacimiento = null;
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaNacimiento = sdf.parse(fechaNacimientoStr);
        } else {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        
           // Buscar la historia clínica ya registrada por su ID
             Historia historia = null;
            if (historiaId != null && !historiaId.isEmpty()) {
                // Usar el ID para buscar la historia existente
                historia = control.buscarHistoria(Integer.parseInt(historiaId));
                if (historia == null) {
                    throw new IllegalArgumentException("La historia clínica no existe.");
                }
            } else {
                throw new IllegalArgumentException("El paciente debe tener una historia clínica.");
            }
           Responsable responsable = null;

            // Verificar si el paciente es menor de edad
        boolean esMayorDeEdad = request.getParameter("esMayorDeEdad") != null;

        // Crear responsable si el paciente es menor de edad
        if (!esMayorDeEdad) {
            // Crear responsable si no está marcado el checkbox (es menor de edad)
            String nombreResponsable = request.getParameter("nombreResponsable");
            String apellidoResponsable = request.getParameter("apellidoResponsable");
            String dniResponsable = request.getParameter("dniResponsable");
            String telefonoResponsable = request.getParameter("telefonoResponsable");
            String direccionResponsable = request.getParameter("direccionResponsable");
            String tipoResponsable = request.getParameter("tipoResponsable");
            String fechaNacimientoResponsableStr = request.getParameter("fechaNacimientoResponsable");

            Date fechaNacimientoResponsable = null;
            if (fechaNacimientoResponsableStr != null && !fechaNacimientoResponsableStr.isEmpty()) {
                SimpleDateFormat sdfResponsable = new SimpleDateFormat("yyyy-MM-dd");
                fechaNacimientoResponsable = sdfResponsable.parse(fechaNacimientoResponsableStr);
            }
             responsable = control.crearResponsable(tipoResponsable, dniResponsable, nombreResponsable, 
                                                        apellidoResponsable, telefonoResponsable, 
                                                        direccionResponsable, fechaNacimientoResponsable); 
         
        } 

       // Llamar al método de la Controladora para crear el paciente
        control.crearPaciente(tieneOS, tipoSangre, responsable, historia, dni, nombre, apellido, telefono, direccion, fechaNacimiento);
        
        
        // Redirigir a una página de éxito
        response.sendRedirect("index.jsp");

    } catch (Exception e) {
        // Si ocurre un error, loguearlo y redirigir a una página de error
        response.sendRedirect("error.jsp?mensaje=" + e.getMessage());
    }
    }
    

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
