/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.servlet;

import ea.ejb.UsuarioFacade;
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
 * @author Joseantpr
 */
@WebServlet(name = "SeguirNoSeguirServlet", urlPatterns = {"/SeguirNoSeguirServlet"})
public class SeguirNoSeguirServlet extends HttpServlet {

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
            Usuario usuarioPropio = (Usuario) sesion.getAttribute("usuario");
        //Usuario usuarioMuro = (Usuario) sesion.getAttribute("usuarioMuro");

            BigDecimal idUsuarioMuro = new BigDecimal(request.getParameter("usuariomuro"));

            BigDecimal idUsuario;
            Usuario usuario;
            String ruta = (String) request.getParameter("ruta");
            String button = (String) request.getParameter("botonSeguir");
            String datos = (String) request.getParameter("datos");

            if (button.equals("Seguir")) {
                idUsuario = new BigDecimal(request.getParameter("usuarioSeguir"));
                usuario = usuarioFacade.find(idUsuario);
                usuario.getUsuarioCollection1().add(usuarioPropio);
                usuarioPropio.getUsuarioCollection().add(usuario);

                usuarioFacade.edit(usuario);
                usuarioFacade.edit(usuarioPropio);

                if (ruta.equals("busquedaUsuarios")) {
                    response.sendRedirect(request.getContextPath() + "/BuscarServlet?buscar=" + datos);
                } else {
                    response.sendRedirect(request.getContextPath() + "/ListarSeguidoresServlet?x=" + ruta);
                }

            } else if (button.equals("Siguiendo")) {
                idUsuario = new BigDecimal(request.getParameter("usuarioDejarSeguir"));
                usuario = usuarioFacade.find(idUsuario);
                usuario.getUsuarioCollection1().remove(usuarioPropio);
                usuarioPropio.getUsuarioCollection().remove(usuario);

                usuarioFacade.edit(usuario);
                usuarioFacade.edit(usuarioPropio);

                if (ruta.equals("busquedaUsuarios")) {
                    response.sendRedirect(request.getContextPath() + "/BuscarServlet?buscar=" + datos);
                } else {
                    response.sendRedirect(request.getContextPath() + "/ListarSeguidoresServlet?x=" + ruta);
                }

//              response.sendRedirect(request.getContextPath()+"/ListarSeguidoresServlet?x="+ruta+"&usuarioMuro="+idUsuarioMuro);
            }

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
