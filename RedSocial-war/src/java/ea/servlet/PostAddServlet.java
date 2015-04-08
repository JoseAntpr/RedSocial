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
import java.math.BigInteger;
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
 * @author Jesus
 */
@WebServlet(name = "PostAddServlet", urlPatterns = {"/PostAddServlet"})
public class PostAddServlet extends HttpServlet {

    @EJB
    private PostFacade postFacade;
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

        //Obtener ID de usuario logeado para pasar a MuroServlet
//        String id=session.getId();
//        Usuario u=usuarioFacade.find(id);
        
        String tit=request.getParameter("titulo");
        String descrip=request.getParameter("descripcion");
        String img=request.getParameter("imagen");
        
        BigDecimal idPost=new BigDecimal(postFacade.count()+1*1.0+"");
        //Añadir post con facade persist a base de datos
        Post p=new Post(idPost);
        p.setDescripcion(descrip); //request.getParameter("postContenido")
        p.setFecha(new Date());
        p.setImagen(tit);
        
      
        //this.postFacade.create(post);
        
        //Creo que no es necesario se hace en MuroServlet
        //Collection postCollection=usuario.getPostCollection();
        //postCollection.add(post);
        
        //usuarioFacade.edit(u);
        
        
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
