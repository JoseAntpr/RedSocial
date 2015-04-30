/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.servlet;
import ea.ejb.UsuarioFacade;
import ea.entity.Usuario;
import java.io.IOException;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Azahar
 */
@WebServlet(name = "EditarUsuarioServlet", urlPatterns = {"/EditarUsuarioServlet"})
public class EditarUsuarioServlet extends HttpServlet {
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

            Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");

            String nombre = (String) request.getParameter("nombre"); 
            nombre = new String(nombre.getBytes("ISO-8859-1"),"UTF8");
            String apellidos = (String) request.getParameter("apellidos"); 
            apellidos = new String(apellidos.getBytes("ISO-8859-1"),"UTF8");
            String direccion = (String) request.getParameter("direccion");
            direccion = new String(direccion.getBytes("ISO-8859-1"),"UTF8");
            String localidad = (String) request.getParameter("localidad");
            localidad = new String(localidad.getBytes("ISO-8859-1"),"UTF8");
            String provincia = (String) request.getParameter("provincia"); 
            provincia= new String(provincia.getBytes("ISO-8859-1"),"UTF8");
            String pais = (String) request.getParameter("pais"); 
             pais = new String(pais.getBytes("ISO-8859-1"),"UTF8");
            String email = (String) request.getParameter("email"); 
            email = new String(email.getBytes("ISO-8859-1"),"UTF8");

            usuarioFacade.editarUsuario(usuario, nombre, apellidos, direccion, localidad, provincia, pais, email); 

            //Esto es por como esta implementado el muro:
            BigDecimal idUser = usuario.getIdUsuario();
            request.getSession().setAttribute("idUser", idUser);
            
            response.sendRedirect(request.getContextPath()+"/MuroServlet?usuarioMuro="+request.getSession().getAttribute("idUser"));
              
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
