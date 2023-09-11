<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.fvc_gestortareas.tarea"%>
<%
    if(session.getAttribute("listatarea")== null){
        ArrayList<tarea> tareaaux = new ArrayList<tarea>();
        session.setAttribute("listatarea", tareaaux);
    }
    ArrayList<tarea> tarea =(ArrayList<tarea>)session.getAttribute("listatarea");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>GESTOR DE TAREAS</h1>
        <h2>Unv. Fidel Vargas Condori</h2>
        <a href="MainServlet?op=nuevo">Nuevo</a>
        <br>
        <table cellspacing=0 border=1>
            <tr>
                <th>Id</th>
                <th>Tareas</th>
                <th>Completado</th>
                <th></th>
                <th></th>
            </tr>
            <%
                if(tarea != null){
                    for(tarea item : tarea){
                        
                 
            %>
            <tr>
                <td><%=item.getId()%></td>
                <td><%=item.getTarea()%></td>
                <td>
                    <input type="checkbox">
                </td>
                <td>
                    <a href="MainServlet?op=editar&id=<%=item.getId()%>">Editar</a>
                </td>
                <td>
                    <a href="MainServlet?op=eliminar&id=<%=item.getId()%>"
                       onclick="return(confirm('esta seguro de eliminar?'))">Eliminar</a>
                </td>
                 
            </tr>
            <%
                  }
                }
            %>
        </table>
    </body>
</html>
