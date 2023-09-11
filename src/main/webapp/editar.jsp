<%@page import="com.emergentes.fvc_gestortareas.tarea"%>
<%
    tarea reg = (tarea) request.getAttribute("objtarea");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro de tareas</h1>
        <form action="MainServlet" method = "post">
        <table>
            <tr>
                <th>Id</th>
                <th><input type="text" name="id" value="<%=reg.getId()%>" size="2" readonly></th>
            </tr>
            <tr>
                <td>tarea</td>
                <td><input type="text" name="tarea" value="<%=reg.getTarea()%>"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Enviar"></td>
            </tr>
        </table>
        </form>
    </body>
</html>
