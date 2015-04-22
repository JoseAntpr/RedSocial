/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.servlet;

import ea.ejb.PostFacade;
import ea.ejb.UsuarioFacade;
import ea.entity.Post;
import ea.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
 * @author Jesus
 */
@WebServlet(name = "PostAddServlet", urlPatterns = {"/PostAddServlet"})
public class PostAddServlet extends HttpServlet {

    @EJB
    private PostFacade postFacade;
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
        
        //get session of the request
        HttpSession session = request.getSession();
        List<Post> listaPost;
        
        
        BigDecimal idUsuario=new BigDecimal(1.0);
       
//        listaPost = this.postFacade.findByMuroIdUsuario(id_usuario);
        
        
        //Obtener ID de usuario logeado para pasar a MuroServlet
//        String id=session.getId();
//        Usuario u=usuarioFacade.find(id);
        
        String descrip=request.getParameter("descripcion");
        String img=request.getParameter("imagen");
        
        BigDecimal idPost=new BigDecimal(postFacade.count()+1*1.0);
        
        //Lista Post de un Usuario
        Usuario usuario=usuarioFacade.find(idUsuario);
        listaPost=(List)usuario.getPostCollection();
        
        //Añadir post con facade persist a base de datos
        Post p=new Post();
        p.setIdPost(idPost);
        p.setIdUsuario(usuario);
        p.setDescripcion(descrip); //request.getParameter("postContenido")
        p.setFecha(new Date());
        p.setImagen(img);
        
//        //Actualizamos la lista de usuarios en Post
//        ArrayList<Usuario> listaUsers = new ArrayList();
//        listaUsers.add(usuario);
//        p.setUsuarioCollection(listaUsers); 
        
        //Actualizamos la lista de Post del Usuario
        listaPost.add(p);
        
        //Añadimos el post a la BD
        postFacade.create(p);
        
//        // Actualizamos la relacion USUARIO-POST (MURO)
        usuarioFacade.edit(usuario);
        
   
        //Enviarle al MuroServlet la id de usuario
//        request.setAttribute("idUsuario", u.getIdUsuario());
       
        
        //Redirect to MuroServlet que mostrará con findAll todos los post en muro.jsp
        response.sendRedirect(request.getContextPath() + "/MuroServlet");
        
        
        //Redirect to newjsp.jsp ####PRUEBAS####
//        request.setAttribute("postContenido", img);
//        RequestDispatcher rd;
//        rd= this.getServletContext().getRequestDispatcher("/newjsp.jsp");
//        rd.forward(request, response);
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
