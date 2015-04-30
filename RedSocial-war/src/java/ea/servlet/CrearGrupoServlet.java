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
@WebServlet(name = "CrearGrupoServlet", urlPatterns = {"/CrearGrupoServlet"})
public class CrearGrupoServlet extends HttpServlet {
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
        HttpSession session = request.getSession();
        
        String nombre = (String) request.getParameter("nombre");            
        String privacidad = (String) request.getParameter("privacidad");
        
//        BigDecimal sesion = (BigDecimal) request.getSession().getAttribute("idUser");
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        
        Grupo grupo = grupoFacade.nuevoGrupo(usuario,nombre, privacidad);
        
        grupo.getUsuarioCollection().add(usuario);
        
        grupoFacade.edit(grupo);
        
        usuario.getGrupoCollection().add(grupo);
        usuarioFacade.edit(usuario);
        
        session.setAttribute("usuarioMuro", usuario);
        
        this.getServletContext().getRequestDispatcher("/GrupoServlet").forward(request, response);
        //this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
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
