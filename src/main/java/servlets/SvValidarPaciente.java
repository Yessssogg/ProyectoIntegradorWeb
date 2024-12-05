
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Controladora;
import modelo.Paciente;


@WebServlet(name = "SvValidarPaciente", urlPatterns = {"/SvValidarPaciente"})
public class SvValidarPaciente extends HttpServlet {

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
        
        String dni = request.getParameter("dni");

        if (dni == null || dni.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400: Mala solicitud
            response.getWriter().write("Por favor ingrese un número de DNI.");
            System.out.println("        LLego hasta a qui ");
            return;
        }
        Paciente paciente = control.buscarPacientePorDNI(dni);
         System.out.println("LLehgo hast aq ui busco");

         if (paciente != null) {
            response.setStatus(HttpServletResponse.SC_OK);  // 200: Todo OK
            response.getWriter().write("Paciente encontrado: " + paciente.getNombre() + " " + paciente.getApellido());
             System.out.println("LLehgo hast aq ui 2");
         } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 404: No encontrado
            response.getWriter().write("Paciente no encontrado.");
        }
    
    
    }
    

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
