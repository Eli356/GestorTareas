
package com.emergentes.gestor_tareas_elizabeth;

import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "TareaServlet", urlPatterns = {"/TareaServlet"})
public class TareaServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        List<Tarea> tareas = obtenerListaTareas(session);
        request.setAttribute("tareas", tareas);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Tarea> tareas = obtenerListaTareas(session);
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "crear":
                    String tareaTexto = request.getParameter("tarea");
                    boolean completado = Boolean.parseBoolean(request.getParameter("completado"));
                    Tarea nuevaTarea = new Tarea(tareas.size() + 1, tareaTexto, completado);
                    tareas.add(nuevaTarea);
                    break;
                case "editar":
                    int idEditar = Integer.parseInt(request.getParameter("idEditar"));
                    String nuevaTareaTexto = request.getParameter("nuevaTareaTexto");
                    boolean completadoEditar = Boolean.parseBoolean(request.getParameter("completadoEditar"));
                    for (Tarea tarea : tareas) {
                        if (tarea.getId() == idEditar) {
                            tarea.setTarea(nuevaTareaTexto);
                            tarea.setCompletado(completadoEditar);
                            break;
                        }
                    }
                     break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("idEliminar"));
                List<Tarea> tareasEliminar = new ArrayList<>();
                for (Tarea tarea : tareas) {
                    if (tarea.getId() != idEliminar) {
                        tareasEliminar.add(tarea);
                    }
                }
                tareas = tareasEliminar;
                break;
        }
    }

    session.setAttribute("listaTareas", tareas);
    response.sendRedirect(request.getContextPath() + "/TareaServlet");
}

    private List<Tarea> obtenerListaTareas(HttpSession session) {
        List<Tarea> tareas = (List<Tarea>) session.getAttribute("listaTareas");
        if (tareas == null) {
            tareas = new ArrayList<>();
            session.setAttribute("listaTareas", tareas);
        }
        return tareas;
    }
}
