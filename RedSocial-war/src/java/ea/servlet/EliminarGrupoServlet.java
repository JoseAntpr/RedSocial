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
import java.math.BigInteger;
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
@WebServlet(name = "EliminarGrupoServlet", urlPatterns = {"/EliminarGrupoServlet"})
public class EliminarGrupoServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
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
        if (request.getSession().getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            //        BigDecimal idGrupo = new BigDecimal((String) request.getParameter("idGrupo"));
//        Grupo grupo = grupoFacade.find(idGrupo);
            HttpSession session = request.getSession();

            Usuario usuario = (Usuario) session.getAttribute("usuario");
            Usuario usuarioMuro = (Usuario) session.getAttribute("usuarioMuro");

            if (usuario.getIdUsuario().equals(usuarioMuro.getIdUsuario())) {

                BigDecimal idGrupoEliminiar = new BigDecimal((String) request.getParameter("idGrupo"));
                Grupo grupoEliminar = grupoFacade.find(idGrupoEliminiar);

                // Solo si el usuario es administrador del grupo puede borrar el grupo
                if (usuario.getIdUsuario().equals(new BigDecimal(grupoEliminar.getIdAdministrador().doubleValue()))) {

                    // Elimina el grupo de todos los miembros y devuelve el usuario sesion actualizado
                    grupoFacade.eliminarGrupo(grupoEliminar, usuario);

                    session.setAttribute("usuario", usuario);
                    session.setAttribute("usuarioMuro", usuario);
                }

            }

            response.sendRedirect(request.getContextPath() + "/GrupoServlet");
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
