
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Controladora;
import modelo.Turno;


@WebServlet(name = "SvTurno", urlPatterns = {"/SvTurno"})
public class SvTurno extends HttpServlet {

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
        System.out.println("llego hata aqui 444");
         // Recibir parámetros del formulario
    String fechaTurnoStr = request.getParameter("fecha_turno"); // Formato yyyy-MM-dd
    String horaTurno = request.getParameter("hora_turno");      // Formato HH:mm
    String afeccion = request.getParameter("afeccion");
    String doctorId = request.getParameter("doctor");           // ID del doctor recibe el nombre 
    String pacienteDni = request.getParameter("dni"); 
    String DNI = "12345678";
    System.out.println("DNI recibido en el servlet: " + DNI);// DNI del paciente
System.out.println("llego hata aqui 444");
    // Validar datos recibidos
    
    try {
        Date fechaTurno = null;
        // Convertir la fecha del turno de String a java.sql.Date
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaTurno = sdf.parse(fechaTurnoStr);
        int i=Integer.parseInt(doctorId);
           System.out.println("nuemro id:"+i+"numer");
        // Llamar a la lógica de negocio para registrar el turno   
        Turno nuevoTurno = control.crearTurno(fechaTurno, horaTurno, afeccion, i, pacienteDni);
        System.out.println("llego hata aqui 444");
        if (nuevoTurno != null) {
            // Redirigir a una página de éxito
            response.sendRedirect("index.jsp");
        } else {
            // Redirigir a una página de error
            response.sendRedirect("error.jsp?error=Error%20al%20crear%20el%20turno");
        }
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
        response.sendRedirect("error.jsp?error=Fecha%20invalida");
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("error.jsp?error=Error%20desconocido");
    }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
