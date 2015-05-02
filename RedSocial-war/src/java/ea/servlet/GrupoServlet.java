/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.servlet;

import ea.ejb.GrupoFacade;
import ea.ejb.PostFacade;
import ea.ejb.UsuarioFacade;
import ea.entity.Grupo;
import ea.entity.Post;
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
    private PostFacade postFacade;
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

        //Recuperamos la sesion completa
        HttpSession session = request.getSession();
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Usuario usuarioMuro = (Usuario) session.getAttribute("usuarioMuro");
        
        // Lista de Grupos donde usuario es miembro
        List<Grupo> listaGruposUsuarioMuro = null;
        
        Grupo grupo = null;
        List<Post> listaPostGrupo = null;
        List<Usuario> listaMiembrosGrupo = null;
        
        // Obtiene grupos públicos y no los privados si usuario != usuarioMuro
        Boolean muroDeOtro = !usuario.getIdUsuario().equals(usuarioMuro.getIdUsuario());
        request.setAttribute("muroDeOtro", muroDeOtro);
        if (muroDeOtro){
            listaGruposUsuarioMuro = (List)usuarioFacade.gruposPublicosDeUsuario(usuarioMuro);
        }else{
            listaGruposUsuarioMuro = (List) usuarioMuro.getGrupoCollection();
        }

        
        
        Boolean tieneGrupos = listaGruposUsuarioMuro.size() > 0;
        request.setAttribute("tieneGrupos", tieneGrupos);
        if (tieneGrupos) {
            
             // Lista de grupos del usuario
            request.setAttribute("listaGruposUsuarioMuro", listaGruposUsuarioMuro);
            
            
            String idGrupoElegido = (String) request.getParameter("idGrupoElegido");
            if(idGrupoElegido != null){
                // Recuperamos el grupo clickado
                BigDecimal idGrupo = new BigDecimal(idGrupoElegido);
//                request.setAttribute("idGrupoElegido", idGrupoElegido);
                grupo = (Grupo) grupoFacade.find(idGrupo);
            }else{
                // Mostramos el primer grupo
//                grupo = (Grupo) listaGruposUsuarioMuro.get(0);
                grupo = (Grupo) grupoFacade.find(listaGruposUsuarioMuro.get(0).getIdGrupo());
            }
            
            // Grupo a mostrar en grupo.jsp
            request.setAttribute("grupo", grupo);
            
            // Lista de post
//            listaPostGrupo = (List) grupo.getPostCollection();
            listaPostGrupo = (List) postFacade.getListaPostGrupo(grupo.getIdGrupo());
            request.setAttribute("listaPostGrupo", listaPostGrupo);

            // Lista de miembros
            listaMiembrosGrupo = (List) grupo.getUsuarioCollection();
            request.setAttribute("listaMiembrosGrupo", listaMiembrosGrupo);
            
            // Recuperamos el post a editar de grupo.jsp si es que lo hay
            String idPostEditar = (String)request.getParameter("idPostEditar");
            // Enviamos el post a editar a grupo.jsp para que lo muestre editable
            if (idPostEditar != null){
                request.setAttribute("idPostEditar", idPostEditar);
            }
            
        }

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
