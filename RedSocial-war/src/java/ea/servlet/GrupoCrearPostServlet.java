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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


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
//        HttpSession session = request.getSession();
//        BigDecimal id_miembro = session.getAttribute("idUser");
        // recuperamos el miembro
        BigDecimal id_miembro = new BigDecimal(1.0);
        Usuario miembro = usuarioFacade.find(id_miembro);

        // recuperamos el grupo
        BigDecimal id_grupo = new BigDecimal(1.0);
        Grupo grupo = grupoFacade.find(id_grupo);

        // OBTENER DESCRIPCIÓN Y SUBIR LA IMAGEN
        Map<String,String> mapDatos = postFacade.obtenerDatosPost(request);
        
        // Creamos el Post
        Post post = new Post();
        post.setIdUsuario(miembro);
        post.setIdGrupo(grupo);
        post.setDescripcion(mapDatos.get("descripcion"));
        post.setFecha( new Date());
        post.setImagen(mapDatos.get("imagen"));

        // Añadimos el post a la DB
        postFacade.create(post);

        // Añadimos el post a la coleccion de post del grupo
        Collection postCollectionGrupo = grupo.getPostCollection();
        postCollectionGrupo.add(post);

        // Actualizamos la relacion POST-GRUPO (MURO_GRUPO)
        grupoFacade.edit(grupo);

        // Añadimos el post a la coleccion de post del miembro creador
        Collection postCollectionUser = miembro.getPostCollection();
        postCollectionUser.add(post);

        // Actualizamos la relacion USUARIO-POST (MURO)
        usuarioFacade.edit(miembro);

        //redirect to the grupo servlet 
        response.sendRedirect(request.getContextPath() + "/GrupoServlet");

    }
    
    

    void depura(String cadena) {
        System.out.println("El error es " + cadena);
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
