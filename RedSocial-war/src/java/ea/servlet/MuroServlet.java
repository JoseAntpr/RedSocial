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
        
        List<Post> listaPost=null;
       
        String idUsuarioMuroGetString=request.getParameter("usuarioMuroGet");
        
        
        String mensaje=null;
        
        if(idUsuarioMuroGetString!=null){
            BigDecimal idUsuarioMuroGet = new BigDecimal(idUsuarioMuroGetString);
            Usuario usuarioMuroGet= usuarioFacade.find(idUsuarioMuroGet);
            if(!usuarioMuro.getIdUsuario().equals(idUsuarioMuroGet)){
                sesion.setAttribute("usuarioMuro", usuarioMuroGet);
                usuarioMuro=usuarioMuroGet;
            }
        }else{
            sesion.setAttribute("usuarioMuro", usuario);
            usuarioMuro=usuario;
        }
        
        if(usuarioMuro.getIdUsuario().equals(usuario.getIdUsuario())){
            listaPost=postFacade.findByMuroIdUsuario(usuario.getIdUsuario());

        }else{

            if(usuario.siguesUsuario(usuarioMuro).equals("si")){
                
                listaPost=postFacade.findByMuroIdUsuario(usuarioMuro.getIdUsuario());
                
                
            }else{
                listaPost=postFacade.findByMuroIdUsuario(usuario.getIdUsuario());
                sesion.setAttribute("usuarioMuro", usuario);
                usuarioMuro = usuario;
//                response.encodeURL("/MuroServlet"); //QUITAR DE LA URL LA ID DEL usuarioMuro
                mensaje = "Error, no puedes ver el muro de un usuario que no sigues."; 
            }
        }
        
        request.setAttribute("mensajeErrorMuroOtro", mensaje);
//        request.setAttribute("usuarioMuro", usuarioMuro);
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
