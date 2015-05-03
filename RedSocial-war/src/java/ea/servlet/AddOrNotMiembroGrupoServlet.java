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
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jose Sánchez Aranda
 */
@WebServlet(name = "AddOrNotMiembroGrupoServlet", urlPatterns = {"/AddOrNotMiembroGrupoServlet"})
public class AddOrNotMiembroGrupoServlet extends HttpServlet {

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
            HttpSession sesion = request.getSession();

            BigDecimal idGrupo = new BigDecimal((String) request.getParameter("idGrupo"));
            BigDecimal idUsuario = new BigDecimal(request.getParameter("idUsuario"));

            Grupo grupo = (Grupo) grupoFacade.find(idGrupo);
            Usuario usuario = usuarioFacade.find(idUsuario);
            String button = (String) request.getParameter("boton");
            String datos = (String) request.getParameter("datos");

            if (button.charAt(0) == 'A') {
                grupo.getUsuarioCollection().add(usuario);
                grupoFacade.edit(grupo);

                usuario.getGrupoCollection().add(grupo);
                usuarioFacade.edit(usuario);

            } else {
                grupo.getUsuarioCollection().remove(usuario);
                grupoFacade.edit(grupo);

                usuario.getGrupoCollection().remove(grupo);
                usuarioFacade.edit(usuario);
            }

            response.sendRedirect(request.getContextPath() + "/AddMiembroGrupoServlet?idGrupo=" + grupo.getIdGrupo().toString());

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
