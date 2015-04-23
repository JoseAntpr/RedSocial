/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.servlet;

import ea.ejb.GrupoFacade;
import ea.entity.Grupo;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fran
 */
@WebServlet(name = "GrupoServlet", urlPatterns = {"/GrupoServlet"})
public class GrupoServlet extends HttpServlet {
    @EJB
    private GrupoFacade grupoFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // IDs
        BigDecimal id_grupo = (BigDecimal) request.getSession().getAttribute("idUser");;
        Grupo grupo = grupoFacade.find(id_grupo);
        
        // Posts del grupo actual
        Collection postCollection;
        postCollection = grupo.getPostCollection();
        request.setAttribute("postGrupo", postCollection);
       
        // Miembros del grupo actual
        Collection miembroCollection;
        miembroCollection = grupo.getUsuarioCollection();
        request.setAttribute("miembrosGrupo", miembroCollection);
        
        // Nombre del grupo actual
        request.setAttribute("nombreGrupo", grupo.getNombre());
        
        // Lista de todos los grupos
        Collection listaGrupos;
        listaGrupos = grupoFacade.findAll();
        request.setAttribute("listaGrupo", listaGrupos);
        
        // Request Dispatcher
        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/grupo.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
