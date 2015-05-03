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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Azahar
 */
@WebServlet(name = "NuevoUsuarioServlet", urlPatterns = {"/NuevoUsuarioServlet"})
public class NuevoUsuarioServlet extends HttpServlet {

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
        if (request.getSession().getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            String nombre = (String) request.getParameter("nombre");
            nombre = new String(nombre.getBytes("ISO-8859-1"), "UTF8");

            String apellidos = (String) request.getParameter("apellidos");
            apellidos = new String(apellidos.getBytes("ISO-8859-1"), "UTF8");

            String direccion = (String) request.getParameter("direccion");
            direccion = new String(direccion.getBytes("ISO-8859-1"), "UTF8");

            String localidad = (String) request.getParameter("localidad");
            localidad = new String(localidad.getBytes("ISO-8859-1"), "UTF8");

            String provincia = (String) request.getParameter("provincia");
            provincia = new String(provincia.getBytes("ISO-8859-1"), "UTF8");

            String pais = (String) request.getParameter("pais");
            pais = new String(pais.getBytes("ISO-8859-1"), "UTF8");

            String email = (String) request.getParameter("email");
            email = new String(email.getBytes("ISO-8859-1"), "UTF8");

            String password = (String) request.getParameter("password");
            password = new String(password.getBytes("ISO-8859-1"), "UTF8");

            Usuario user = usuarioFacade.buscarEmail(email);

            if (user == null) {

                user = usuarioFacade.nuevoUser(nombre, apellidos, direccion, localidad, provincia, pais, email, password);

                BigDecimal idUser = user.getIdUsuario();
//            request.getSession().setAttribute("idUser", idUser);
                //USUARIO SESIÓN
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", user);

                //USUARIO CAMBIANTE
                sesion.setAttribute("usuarioMuro", user);

                response.sendRedirect(request.getContextPath() + "/MuroServlet");
//            response.sendRedirect(request.getContextPath()+"/MuroServlet?usuarioMuro="+request.getSession().getAttribute("idUser"));

            } else {

                String mensaje = "El email ya esta registrado en nuestra red social.";
                request.setAttribute("mensaje", mensaje);
                this.getServletContext().getRequestDispatcher("/loginError.jsp").forward(request, response);
            }
        }
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
