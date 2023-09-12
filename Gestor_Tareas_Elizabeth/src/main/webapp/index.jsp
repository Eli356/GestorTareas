<%-- 
    Document   : index
    Created on : 12 sep. de 2023, 13:17:35
    Author     : SONY VAIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>Lista de Tareas</h1>
         <h1>Elizabeth Juliana Gutierrez Quispe</h1>

    <!-- Formulario para crear una nueva tarea -->
    <form action="/TareaServlet" method="post">
        <input type="hidden" name="action" value="crear">
        <label for="tarea">Nueva Tarea:</label>
        <input type="text" name="tarea" required>
        <label for="completado">Completado:</label>
        <select name="completado">
            <option value="true">✔</option>
            <option value="false">✗</option>
        </select>
        <button type="submit">Guardar Tarea</button>
    </form>

    <!-- Lista de Tareas -->
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tarea</th>
                <th>Completado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${tareas}" var="tarea">
                <tr>
                    <td>${tarea.getId()}</td>
                    <td>${tarea.getTarea()}</td>
                    <td>${tarea.isCompletado() ? '✔' : '✗'}</td>
                    <td>
                        <form action="/TareaServlet" method="post">
                            <input type="hidden" name="action" value="editar">
                            <input type="hidden" name="idEditar" value="${tarea.getId()}">
                            <input type="text" name="nuevaTareaTexto" required>
                            <select name="completadoEditar">
                                <option value="true">✔</option>
                                <option value="false">✗</option>
                            </select>
                            <button type="submit">Editar</button>
                        </form>
                        <form action="/TareaServlet" method="post">
                            <input type="hidden" name="action" value="eliminar">
                            <input type="hidden" name="idEliminar" value="${tarea.getId()}">
                            <button type="submit">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
                
    </body>
</html>
