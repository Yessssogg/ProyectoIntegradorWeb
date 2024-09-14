package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Doctor;
import logica.Horario;
import logica.Usuario;

@WebServlet(name = "SvEditDoctor", urlPatterns = {"/SvEditDoctor"})
public class SvEditDoctor extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        try {
            int idDoctor = Integer.parseInt(request.getParameter("id"));
            Doctor doctor = control.buscarDoctor(idDoctor);

            // Cargar los datos del doctor en la sesión para ser utilizados en el JSP
            HttpSession session = request.getSession();
            session.setAttribute("doctorEditar", doctor);

            // Redirigir al JSP de edición
            response.sendRedirect("editarDoctores.jsp");

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
            int idDoctor = Integer.parseInt(request.getParameter("id"));
            String especialidad = request.getParameter("especialidad");
            String horarioInicio = request.getParameter("horario_inicio");
            String horarioFin = request.getParameter("horario_fin");

            // Buscar el doctor y su horario
            Doctor doctor = control.buscarDoctor(idDoctor);
            if (doctor != null) {
                Horario horario = doctor.getUnHorario();
                if (horario != null) {
                    horario.setHorario_inicio(horarioInicio);
                    horario.setHorario_fin(horarioFin);

                    // Actualizar el horario
                    control.editarHorario(horario);
                }

                // Actualizar la especialidad del doctor
                doctor.setEspecialidad(especialidad);

                // Actualizar el doctor
                control.editarDoctor(doctor);
            }

            // Redirigir a la lista de doctores o a una página de éxito
            response.sendRedirect("SvDoctor");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos del formulario no válidos.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar los datos del doctor.");
        }
    
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
