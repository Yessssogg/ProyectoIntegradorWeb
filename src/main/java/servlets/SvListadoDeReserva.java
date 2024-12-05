package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Controladora;
import modelo.Doctor;
import modelo.Paciente;

@WebServlet(name = "SvListadoDeReserva", urlPatterns = {"/SvListadoDeReserva"})
public class SvListadoDeReserva extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Paciente> listaPacientes = control.getPacientes();

        // Obtener listas de doctores, pacientes y especialidades
        List<Doctor> listaDoctores = control.getDoctores();

        Set<String> especialidadesUnicas = new HashSet<>();
        for (Doctor doctor : listaDoctores) {
            especialidadesUnicas.add(doctor.getEspecialidad());

        }
        List<String> listaEspecialidades = new ArrayList<>(especialidadesUnicas);
        // Guardar listas en la sesión
        // Guardar listas en la sesión
        HttpSession misession = request.getSession();
        misession.setAttribute("listaEspecialidades", listaEspecialidades);
        misession.setAttribute("listaDoctores", listaDoctores);
        misession.setAttribute("listaPacientes", listaPacientes);

// Redirigir al formulario JSP
        response.sendRedirect("altaReserva.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
