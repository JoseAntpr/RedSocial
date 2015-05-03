/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.servlet;

import ea.ejb.GrupoFacade;
import ea.ejb.PostFacade;
import ea.entity.Grupo;
import ea.entity.Post;
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
 * @author Jesus
 */
@WebServlet(name = "PostDeleteServlet", urlPatterns = {"/PostDeleteServlet"})
public class PostDeleteServlet extends HttpServlet {
    @EJB
    private PostFacade postFacade;

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

        //get session of the request
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        String tipoBorrado = request.getParameter("tipo_borrado");
        String idGuardada = request.getParameter("idGuardada");
        BigDecimal idPost = new BigDecimal(idGuardada);

        if (tipoBorrado.equals("usuario")) {

            //listapostUsuario.remove
            //postFacade.remove(idPost);
            //usuarioFacade.edit(usuario)
            postFacade.deletePost(idPost);

            response.sendRedirect(request.getContextPath() + "/MuroServlet");
//            response.sendRedirect(request.getContextPath()+"/MuroServlet?usuarioMuro="+usuario.getIdUsuario());
        } else if (tipoBorrado.equals("from_grupo")) {
            postFacade.deletePostGrupo(idPost, usuario);
            
            
            response.sendRedirect(request.getContextPath() + "/GrupoServlet?idGrupoElegido=" + request.getParameter("idGrupoElegido"));
//            response.sendRedirect(request.getContextPath() + "/GrupoServlet?usuarioMuro=" + usuario.getIdUsuario());
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
