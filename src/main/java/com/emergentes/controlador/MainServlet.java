
package com.emergentes.controlador;

import com.emergentes.fvc_gestortareas.tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String op=request.getParameter("op");
       tarea objtar = new tarea();
       int id,pos;
       
       HttpSession ses = request.getSession();
       ArrayList<tarea> lista = (ArrayList<tarea>)ses.getAttribute("listatarea");
       switch(op){
           case "nuevo":
               request.setAttribute("objtarea", objtar);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               
               break;
           case "editar":
               id= Integer.parseInt(request.getParameter("id"));
               //averiguar la posision
               pos = buscarPorIndice(request,id);
               //obtener el objeto
               objtar = lista.get(pos);
               request.setAttribute("objtarea", objtar);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               break;
           case"eliminar":
               id= Integer.parseInt(request.getParameter("id"));
               pos = buscarPorIndice(request,id);
               if(pos>=0){
                   lista.remove(pos);
               }
               request.setAttribute("listatarea", lista);
               response.sendRedirect("index.jsp");
               break;
           default:
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<tarea> lista = (ArrayList<tarea>) ses.getAttribute("listatarea");
        tarea objtar = new tarea();
        objtar.setId(id);
        objtar.setTarea(request.getParameter("tarea"));
        
        if(id==0){
            //nuevo registro
            int idNuevo = obtenerId(request);
            objtar.setId(idNuevo);
            lista.add(objtar);
        }
        else{
            int pos = buscarPorIndice(request,id);
            lista.set(pos, objtar);
            
        }
        request.setAttribute("listatarea", lista);
        response.sendRedirect("index.jsp");
      
    }
    
    public int buscarPorIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<tarea> lista =(ArrayList<tarea>) ses.getAttribute("listatarea");
        int pos =-1;
        if(lista !=null){
            for(tarea ele : lista){
                ++pos;
                if(ele.getId() == id){
                    break;
                }
            }
        }
        return pos;
    }
    public int obtenerId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<tarea> lista =(ArrayList<tarea>) ses.getAttribute("listatarea");
        int idn = 0;
        for(tarea ele : lista){
            idn = ele.getId();
        }
        return idn + 1;
    }
}
