/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.servlet;

import ea.ejb.UsuarioFacade;
import ea.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
 * @author Joseantpr
 */
@WebServlet(name = "ListarSeguidoresServlet", urlPatterns = {"/ListarSeguidoresServlet"})
public class ListarSeguidoresServlet extends HttpServlet {

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

        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        Usuario usuarioMuro = (Usuario) sesion.getAttribute("usuarioMuro");
        
        List<Usuario> ListaSeguidores = null;
        String x = (String) request.getParameter("x");

//        BigDecimal idUsuario=new BigDecimal( request.getParameter("usuarioMuro"));
//        Usuario  usuarioMuro=usuarioFacade.find(idUsuario);

        if (x.equals("seguidores")) {

            ListaSeguidores = (List) usuarioMuro.getUsuarioCollection1();

        } else if (x.equals("Seguir")) {

            ListaSeguidores = (List) usuarioMuro.getUsuarioCollection();

        } else if (x.equals("usuariosSeguir")) {
            ListaSeguidores = usuarioFacade.findAll();

        }

        request.setAttribute("x", x);
//        request.setAttribute("usuarioMuro", usuarioMuro);
        request.setAttribute("listaSeguidores", ListaSeguidores);
        
        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/seguidores.jsp");
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
