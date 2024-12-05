
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Controladora;


@WebServlet(name = "SvEliminarPaciente", urlPatterns = {"/SvEliminarPaciente"})
public class SvEliminarPaciente extends HttpServlet {

     Controladora control = new Controladora();
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int idPaciente = Integer.parseInt(request.getParameter("id"));
        
        try {
            // Llamar a la lógica de borrar doctor
            control.borrarPaciente(idPaciente);
            
            
        } catch (Exception ex) {
            Logger.getLogger(SvEliminarDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        response.sendRedirect("SvPaciente");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
