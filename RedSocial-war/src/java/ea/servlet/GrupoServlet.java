/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.servlet;

import ea.ejb.UsuarioFacade;
import ea.entity.Grupo;
import ea.entity.Post;
import ea.entity.Usuario;
import java.io.IOException;
import java.math.BigDecimal;
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

        //Recuperamos la sesion completa
        HttpSession session = request.getSession();
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        String id = request.getParameter("usuarioMuro");
        BigDecimal idUsuarioMuro = new BigDecimal(id);
        Usuario usuarioMuro = usuarioFacade.find(idUsuarioMuro);

        // Lista de Grupos donde usuario es miembro
        List<Grupo> listaGruposUsuario = (List) usuario.getGrupoCollection();
        

        Grupo grupo;
        List<Post> listaPostGrupo;
        List<Usuario> listaMiembrosGrupo;
        
        Boolean tieneGrupos = listaGruposUsuario.size() > 0;
        request.setAttribute("tieneGrupos", tieneGrupos);
        if (tieneGrupos) {
            request.setAttribute("listaGruposUsuario", listaGruposUsuario);
            
            // primer grupo de la lista
            grupo = (Grupo) listaGruposUsuario.get(0);
            request.setAttribute("grupo", grupo);

            // Lista de post
            listaPostGrupo = (List<Post>) grupo.getPostCollection();
            request.setAttribute("listaPostGrupo", listaPostGrupo);

            // Lista de miembros
            listaMiembrosGrupo = (List<Usuario>) grupo.getUsuarioCollection();
            request.setAttribute("listaMiembrosGrupo", listaMiembrosGrupo);

            
        }

        // Usuario Muro
        request.setAttribute("usuarioMuro", usuarioMuro);

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
