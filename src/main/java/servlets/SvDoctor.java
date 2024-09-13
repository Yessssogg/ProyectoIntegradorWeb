
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Doctor;


@WebServlet(name = "SvDoctor", urlPatterns = {"/SvDoctor"})
public class SvDoctor extends HttpServlet {

   
    Controladora control = new Controladora();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        
        
    }
  
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        List<Doctor>listaDoctores = new ArrayList<Doctor>();
        
        listaDoctores = control.getDoctores();
       HttpSession misession = request.getSession();
      misession.setAttribute("listaDoctores", listaDoctores);
        
      response.sendRedirect("verMedicos.jsp");
        
        
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("Nombre");
        String apellido = request.getParameter("Apellido");
        String telefono = request.getParameter("Telefono");
        String direccion = request.getParameter("Direccion");
        String fechaNacStr = request.getParameter("fecha_nac");
        String especialidad = request.getParameter("Especialidad");
        String rol = "Medico";

        // Datos de Usuario
        String nombreUsuario = request.getParameter("Usuario");
        String contrasena = request.getParameter("Contrasena");

        // Datos de Horario
        String horarioInicio = request.getParameter("Horario_inicio");
        String horarioFin = request.getParameter("Horario_fin");

        // Formato de la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNac = null;
        try {
            fechaNac = sdf.parse(fechaNacStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        control.crearDoctor(especialidad, nombreUsuario, contrasena, rol, dni, nombre, apellido, telefono, direccion, fechaNac, horarioInicio, horarioFin);
         response.sendRedirect("index.jsp");
        
        
    }

   
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
