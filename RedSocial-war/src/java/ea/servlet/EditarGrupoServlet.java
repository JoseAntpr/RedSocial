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
import java.util.List;
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
@WebServlet(name = "EditarGrupoServlet", urlPatterns = {"/EditarGrupoServlet"})
public class EditarGrupoServlet extends HttpServlet {

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
            HttpSession session = request.getSession();

            Usuario usuario = (Usuario) session.getAttribute("usuario");

            boolean editado = false;

            if ((String) request.getParameter("grupoEditado") != null) {
                editado = true;
            }

            if (editado) {
                Grupo grupo = grupoFacade.find(new BigDecimal((String) request.getParameter("idGrupo")));
                usuario.getGrupoCollection().remove(grupo);
                grupo.getUsuarioCollection().remove(usuario);
                String nombreGrupo = (String) request.getParameter("nombreGrupo");
                nombreGrupo = new String(nombreGrupo.getBytes("ISO-8859-1"), "UTF8");
                grupo.setNombre(nombreGrupo);

                if (((String) request.getParameter("privacidad")).toUpperCase().equals("PRIVADO")) {
                    grupo.setPrivacidad(BigInteger.ONE);
                } else {
                    grupo.setPrivacidad(BigInteger.ZERO);
                }
                grupo.getUsuarioCollection().add(usuario);
                grupoFacade.edit(grupo);

                //grupo.getUsuarioCollection().add(usuario);
                usuario.getGrupoCollection().add(grupo);

                usuarioFacade.edit(usuario);

                response.sendRedirect(request.getContextPath() + "/GrupoServlet");

            } else {
                Grupo grupo = grupoFacade.find(new BigDecimal((String) request.getParameter("idGrupo")));
                request.setAttribute("grupo", grupo);
                this.getServletContext().getRequestDispatcher("/editarGrupo.jsp").forward(request, response);
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
