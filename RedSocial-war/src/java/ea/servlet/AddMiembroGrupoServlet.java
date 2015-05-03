/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.servlet;

import ea.ejb.GrupoFacade;
import ea.ejb.UsuarioFacade;
import ea.entity.Grupo;
import ea.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jose Sánchez Aranda
 */
@WebServlet(name = "AddMiembroGrupoServlet", urlPatterns = {"/AddMiembroGrupoServlet"})
public class AddMiembroGrupoServlet extends HttpServlet {

    @EJB
    private GrupoFacade grupoFacade;
    @EJB
    private UsuarioFacade usuarioFacade;

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

        if (request.getSession().getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            Grupo grupo = (Grupo) grupoFacade.find(new BigDecimal((String) request.getParameter("idGrupo")));

            String datosBusqueda = request.getParameter("buscar");
            List<Usuario> listaUsuarios;

            List<Usuario> listaMiembrosGrupo = (List<Usuario>) grupo.getUsuarioCollection();
            request.setAttribute("listaMiembrosGrupo", listaMiembrosGrupo);

            if (datosBusqueda == null) {
                listaUsuarios = (List<Usuario>) usuarioFacade.findAll();
            } else {
                listaUsuarios = (List<Usuario>) usuarioFacade.buscarUsuarios(datosBusqueda);
            }

            request.setAttribute("listaUsuarios", listaUsuarios);
            request.setAttribute("datos", datosBusqueda);
            request.setAttribute("grupo", grupo);

            RequestDispatcher rd;
            rd = this.getServletContext().getRequestDispatcher("/addMiembroGrupo.jsp");
            rd.forward(request, response);
        }
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
