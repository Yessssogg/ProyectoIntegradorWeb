
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Controladora;
import modelo.Historia;


@WebServlet(name = "SvHistoria", urlPatterns = {"/SvHistoria"})
public class SvHistoria extends HttpServlet {

    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    // Recuperar los parámetros del formulario
    String fechaCreacion = request.getParameter("fechaCreacion");
    String alergias = request.getParameter("alergias");
    String enfermedadesPrevias = request.getParameter("enfermedadesPrevias");
    String tratamientosActuales = request.getParameter("tratamientosActuales");
    String cirugiasPrevias = request.getParameter("cirugiasPrevias");
    String observaciones = request.getParameter("observaciones");
    String estado = request.getParameter("estado");
    String proximaCita = request.getParameter("proximaCita");
    
    // Llamar a la controladora que maneja la lógica para crear la Historia
    
    Historia historia = control.crearHistoria(fechaCreacion, alergias, enfermedadesPrevias, 
                                                  tratamientosActuales, cirugiasPrevias, 
                                                  observaciones, estado, proximaCita, null);

   
    // Pasar el objeto Historia al request para que sea accesible en la JSP
    request.setAttribute("historia", historia);
    
    // Redirigir a la página de confirmación (historiaConfirmada.jsp)
    RequestDispatcher dispatcher = request.getRequestDispatcher("altaPaciente.jsp");
    dispatcher.forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
