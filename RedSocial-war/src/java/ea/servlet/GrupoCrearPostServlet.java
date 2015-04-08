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
@WebServlet(name = "GrupoCrearPostServlet", urlPatterns = {"/GrupoCrearPostServlet"})
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
            throws ServletException, IOException {
        
        //get session of the request
//        HttpSession session = request.getSession();
//        
        BigDecimal id_miembro;
//        id_miembro = new BigDecimal((double)session.getAttribute("id_usuario"));
        id_miembro = new BigDecimal(1.0);
        
        
        BigDecimal id_grupo = new BigDecimal(1.0);
        Grupo grupo = grupoFacade.find(id_grupo);
        
        BigDecimal id_post = new BigDecimal((postFacade.count()+1)*1.0);

        
        // Obtenemos las propiedades del request
        String name;
        String description;
        String image = "assets/img/";
        
        try{
            description = request.getParameter("description_post_grupo");
            image += request.getParameter("image_post_grupo");
        }catch(Exception ex){
            RequestDispatcher rd;
            rd = (RequestDispatcher)this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        }
        
//        name = "El mundo";
//        description = "Syriza apuesta por la medida que espanta a los inversores internacionales: auditar los 320.000 millones de euros de deuda, el 180% del PIB.";
//        image += "bg_4.jpg";
        
        // Creamos el Post
        Post post = new Post();
        post.setIdPost(id_post);
        post.setDescripcion(description);
        post.setFecha(new Date());
        post.setImagen(image);
        
        // Añadimos el post a la DB
        postFacade.create(post);
        
        // Añadimos el post a la coleccion de post del grupo
        Collection postCollectionGrupo = grupo.getPostCollection();
        postCollectionGrupo.add(post);
        
        // Actualizamos la relacion POST-GRUPO (MURO_GRUPO)
        grupoFacade.edit(grupo);
        
        // Añadimos el post a la coleccion de post del miembro creador
        Usuario miembro = usuarioFacade.find(id_miembro);
        Collection postCollectionUser = miembro.getPostCollection();
        postCollectionUser.add(post);
        
        // Actualizamos la relacion USUARIO-POST (MURO)
        usuarioFacade.edit(miembro);
        

        //redirect to the grupo servlet 
        response.sendRedirect(request.getContextPath() + "/GrupoServlet");
        
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
