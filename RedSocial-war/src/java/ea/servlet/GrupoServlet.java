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
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fran
 */
@WebServlet(name = "GrupoServlet", urlPatterns = {"/GrupoServlet"})
public class GrupoServlet extends HttpServlet {
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
        
        // Recuperamos la sesion completa
//        HttpSession session = request.getSession();
//        
//        Usuario user = (Usuario)session.getAttribute("user");
//        BigDecimal id_usuario = user.getIdUsuario();
        

        
        //prueba
        BigDecimal id_usuario = new BigDecimal(1.0);
        Usuario user = usuarioFacade.find(id_usuario);
        
//        List<Grupo> listaGruposMiembro = grupoFacade.findGruposByIdMiembro(id_usuario);
        List<Grupo> listaGruposMiembro = (List)user.getGrupoCollection();
        Grupo grupo = listaGruposMiembro.get(0);
        
        
        
        
        
        // Posts del grupo actual
        Collection postCollection;
        postCollection = grupo.getPostCollection();
        request.setAttribute("postGrupo", postCollection);
       
        // Miembros del grupo actual
        Collection miembroCollection;
        miembroCollection = grupo.getUsuarioCollection();
        request.setAttribute("miembrosGrupo", miembroCollection);
        
        // Nombre del grupo actual
        request.setAttribute("grupo", grupo);
        
        // Lista de todos los grupos
        Collection listaGrupos;
        listaGrupos = grupoFacade.findAll();
        request.setAttribute("listaGrupos", listaGrupos);
        
        // Lista de Grupos donde user es miembro
        request.setAttribute("listaGruposMiembro", listaGruposMiembro);
        
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
