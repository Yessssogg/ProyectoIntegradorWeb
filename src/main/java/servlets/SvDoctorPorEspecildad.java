
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Controladora;
import modelo.Doctor;


@WebServlet(name = "SvDoctorPorEspecildad", urlPatterns = {"/SvDoctorPorEspecildad"})
public class SvDoctorPorEspecildad extends HttpServlet {

    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // Obtener el parámetro de especialidad
        String especialidadId = request.getParameter("especialidadId");
        System.out.println("llego hata a qui 2");
        // Verificar si el parámetro especialidadId está presente
        if (especialidadId != null && !especialidadId.isEmpty()) {
            // Llamar al método de la controladora para obtener doctores por especialidad
            List<Doctor> doctores = control.obtenerDoctoresPorEspecialidad(especialidadId);

            // Verificar si se encontraron doctores
            if (doctores != null && !doctores.isEmpty()) {
                // Usar Jackson ObjectMapper para convertir la lista de doctores a JSON
                ObjectMapper objectMapper = new ObjectMapper();

                // Aplicar un filtro para ignorar propiedades no deseadas (relaciones, etc.)
                objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter("DoctorFilter", 
                    SimpleBeanPropertyFilter.serializeAllExcept("listaTurnos", "unUsuario", "unHorario")));

                String json = objectMapper.writeValueAsString(doctores);

                // Configurar la respuesta con tipo de contenido JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Escribir el JSON en la respuesta
                response.getWriter().write(json);
            } else {
                // Si no se encuentran doctores, retornar una respuesta vacía o un error
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);  // 204 No Content
                response.getWriter().write("No se encontraron doctores para esta especialidad.");
            }
        } else {
            // Si no se pasa el parámetro especialidadId, devolver un error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Especialidad no proporcionada");
        }
    
    
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
