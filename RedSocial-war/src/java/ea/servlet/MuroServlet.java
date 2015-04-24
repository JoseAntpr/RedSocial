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
@WebServlet(name = "MuroServlet", urlPatterns = {"/MuroServlet"})
public class MuroServlet extends HttpServlet {
    
    @EJB
    private UsuarioFacade usuarioFacade;
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
       
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        Usuario usuarioMuro = (Usuario) sesion.getAttribute("usuarioMuro");
        
        // inicion proviene de seguidoes
        boolean inicio = new Boolean(request.getParameter("inicio"));
        if(inicio){
            usuarioMuro = usuario;
        }
        
        List<Post> listaPost=null;
       
        
//        BigDecimal idUsuarioMuro = new BigDecimal(request.getParameter("usuarioMuro"));
        BigDecimal idUsuarioMuro = usuarioMuro.getIdUsuario();
//        BigDecimal idUsuario =(BigDecimal)request.getSession().getAttribute("idUser");
        BigDecimal idUsuario = usuario.getIdUsuario();
        
//        Usuario usuario =usuarioFacade.find(idUsuario);
//        Usuario usuarioMuro= usuarioFacade.find(idUsuarioMuro);
        
        String mensaje=null;
//      
        if(idUsuarioMuro.equals(idUsuario)){
            listaPost=postFacade.findByMuroIdUsuario(idUsuario);

        }else{
//            if(usuario.siguesUsuario(usuarioMuro).equals("si")){
            if(usuario.siguesUsuario(usuarioMuro).equals("si")){
                
                listaPost=postFacade.findByMuroIdUsuario(idUsuarioMuro);
                
                
            }else{
                listaPost=postFacade.findByMuroIdUsuario(idUsuario);
//                usuarioMuro=usuario;
                usuarioMuro = usuario;
                mensaje = "Error, no puedes ver el muro de un usuario que no sigues."; 
            }
        }
        
        request.setAttribute("mensajeErrorMuroOtro", mensaje);
//        request.setAttribute("usuarioSesion", usuario);
//        request.setAttribute("usuarioMuro", usuarioMuro);
//        request.setAttribute("usuarioMuro", usuarioMuro);
        sesion.setAttribute("usuarioMuro", usuarioMuro);
        
        request.setAttribute("listaPost", listaPost); //Para mandar listaPost a muro.jsp
        
        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/muro.jsp");
       
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
