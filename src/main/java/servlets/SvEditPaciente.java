
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Controladora;
import modelo.Paciente;


@WebServlet(name = "SvEditPaciente", urlPatterns = {"/SvEditPaciente"})
public class SvEditPaciente extends HttpServlet {

    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            int idPaciente = Integer.parseInt(request.getParameter("id"));
            Paciente paciente = control.buscarPaciente(idPaciente);
            // Cargar los datos del doctor en la sesión para ser utilizados en el JSP
            HttpSession session = request.getSession();
            session.setAttribute("pacienteEditar", paciente);
            // Redirigir al JSP de edición
            response.sendRedirect("editarPaciente.jsp");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de doctor no válido.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al recuperar los datos del doctor.");
        }
        
        
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            // Obtener datos del formulario
            int idPaciente = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String dni = request.getParameter("dni");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String tipoSangre = request.getParameter("tipo_sangre");
            boolean tieneObraSocial = Boolean.parseBoolean(request.getParameter("tiene_os"));
            String fechaNac = request.getParameter("fecha_nac");

            // Buscar el paciente
            Paciente paciente = control.buscarPaciente(idPaciente);
            if (paciente != null) {
                // Actualizar los datos del paciente
                paciente.setNombre(nombre);
                paciente.setApellido(apellido);
                paciente.setDni(dni);
                paciente.setTelefono(telefono);
                paciente.setDireccion(direccion);
                paciente.setTipo_Sangre(tipoSangre);
                paciente.setTiene_OS(tieneObraSocial);

                // Actualizar la fecha de nacimiento
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                if (!fechaNac.isEmpty()) {
                    paciente.setFecha_nac(sdf.parse(fechaNac));
                }

                // Actualizar el paciente
                control.editarPaciente(paciente);
            }

            // Redirigir a la lista de pacientes o a una página de éxito
            response.sendRedirect("SvPaciente");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos del formulario no válidos.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar los datos del paciente.");
        }
    }
    

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
