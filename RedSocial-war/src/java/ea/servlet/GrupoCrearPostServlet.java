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
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author fran
 */
@WebServlet(name = "GrupoCrearPostServlet", urlPatterns = {"/GrupoCrearPostServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
//@MultipartConfig
public class GrupoCrearPostServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PostFacade postFacade;
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
            throws ServletException, IOException, Exception {

        //get session of the request
        HttpSession sesion = request.getSession();
        Usuario miembro = (Usuario) sesion.getAttribute("usuario");

        // OBTENER DESCRIPCIÓN Y SUBIR LA IMAGEN
//        Map<String,String> mapDatosForm = postFacade.obtenerDatosPost(request);
        Map<String,String> mapDatosForm = postFacade.obtenerDatosFormConImagen(request);
        
        
        // Recuperamos el grupo
        BigDecimal id_grupo = new BigDecimal(mapDatosForm.get("id_grupo"));
        Grupo grupo = grupoFacade.find(id_grupo);
        
        // Creamos el Post
        Post post = new Post();
        post.setIdUsuario(miembro);
        post.setIdGrupo(grupo);
        post.setDescripcion(mapDatosForm.get("descripcion"));
        post.setFecha( new Date());
        post.setImagen(mapDatosForm.get("imagen"));

        // Añadimos el post a la DB
        postFacade.create(post);

        // Añadimos el post a la coleccion de post del grupo
        Collection postCollectionGrupo = grupo.getPostCollection();
        postCollectionGrupo.add(post);

        // Actualizamos el grupo con el post ya añadido
        grupoFacade.edit(grupo);

        // Añadimos el post a la coleccion de post del miembro creador
        Collection postCollectionUser = miembro.getPostCollection();
        postCollectionUser.add(post);

        // Actualizamos el usuario con el post ya añadido
        usuarioFacade.edit(miembro);

        //redirect to the grupo servlet 
        response.sendRedirect(request.getContextPath() + "/GrupoServlet?usuarioGrupo=" + miembro.getIdUsuario());

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
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(GrupoCrearPostServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(GrupoCrearPostServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
