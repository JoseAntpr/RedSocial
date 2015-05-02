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
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
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
@WebServlet(name = "GrupoEditarPostServlet", urlPatterns = {"/GrupoEditarPostServlet"})
public class GrupoEditarPostServlet extends HttpServlet {
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private GrupoFacade grupoFacade;
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
        Usuario miembro = (Usuario) session.getAttribute("usuario");
        
        BigDecimal idPostEditar = new BigDecimal(request.getParameter("idPostEditar"));

        // OBTENER DESCRIPCIÓN Y SUBIR LA IMAGEN
        Map<String,String> mapDatosForm = postFacade.obtenerDatosFormConImagen(request);
        
        // Recuperamos el grupo
        BigDecimal id_grupo = new BigDecimal(mapDatosForm.get("id_grupo"));
        Grupo grupo = grupoFacade.find(id_grupo);
  
        // Obtenemos el Post de la BD
        Post post = postFacade.find(idPostEditar);

        // Editamos los cambios si los hay
        String description = mapDatosForm.get("descripcion");
        if(!post.getDescripcion().equals(description)){
            post.setDescripcion(mapDatosForm.get("descripcion"));
        }

        String imagen = mapDatosForm.get("imagen");
        String imagenActual = null;
        imagenActual = post.getImagen();
        if (!imagen.equals("")){
            if (imagenActual != null){
                if(!imagenActual.equals(imagen)){
                    post.setImagen(mapDatosForm.get("imagen"));
                }
            }else{
                post.setImagen(mapDatosForm.get("imagen"));
            }
        }else{
            if(imagenActual != null){
                post.setImagen(mapDatosForm.get("imagen"));
            }
        }
        
        // Editamos el post en la DB
        postFacade.edit(post);
        
        // Actualizo el post en la lista del grupo
        List listPostGrupo = (List)grupo.getPostCollection();
        Integer posPostGrupo = buscaPosicionPost(listPostGrupo, post);
        listPostGrupo.set(posPostGrupo, post);
        grupo.setPostCollection(listPostGrupo);
        
        // Actualizo el grupo en la BD
        grupoFacade.edit(grupo);
        
        // Actualizo el post en la lista del miembro
        List listPostMiembro = (List)miembro.getPostCollection();
        Integer posPostMiembro = buscaPosicionPost(listPostMiembro, post);
        listPostMiembro.set(posPostMiembro, post);
        miembro.setPostCollection(listPostMiembro);
        
        // Actualizo el usuraio en la BD
        usuarioFacade.edit(miembro);
        
        session.setAttribute("usuario", miembro);
        session.setAttribute("usuarioMuro", miembro);
        

        //redirect to the grupo servlet 
        response.sendRedirect(request.getContextPath() + "/GrupoServlet?idGrupoElegido=" + grupo.getIdGrupo());
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
    
    
    public Integer buscaPosicionPost(Collection posts, Post post){
        Integer i = 0;
        List<Post> lista = (List) posts;
        while (!lista.get(i).equals(post)){
            i++;
        }
        return i;
    }

}
