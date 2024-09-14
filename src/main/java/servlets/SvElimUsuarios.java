
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
import logica.Controladora;


@WebServlet(name = "SvElimUsuarios", urlPatterns = {"/SvElimUsuarios"})
public class SvElimUsuarios extends HttpServlet {

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
          int id = Integer.parseInt(request.getParameter("id"));
        
       try {
           control.borrarUsuario(id);
       } catch (Exception ex) {
           Logger.getLogger(SvElimUsuarios.class.getName()).log(Level.SEVERE, null, ex);
       }
        response.sendRedirect("SvUsuarios");
                  
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
