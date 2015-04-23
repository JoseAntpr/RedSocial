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
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author fran
 */
@WebServlet(name = "GrupoCrearPostServlet", urlPatterns = {"/GrupoCrearPostServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class GrupoCrearPostServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PostFacade postFacade;
    @EJB
    private GrupoFacade grupoFacade;

    /**
     * Name of the directory where uploaded files will be saved, relative to the
     * web application directory.
     */
    private static final String SAVE_DIR = "uploadImages";

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

//        BigDecimal id_post = new BigDecimal((postFacade.count() + 1) * 1.0);

        // Obtenemos las propiedades del request
        String description = null;
        String image = null;
        
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        // creates the save directory if it does not exists
//        File fileSaveDir = new File(savePath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdir();
//        }

        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        if (isMultiPart) {
            ServletFileUpload upload = new ServletFileUpload();
            try {
                FileItemIterator it = upload.getItemIterator(request);
                FileItem item;
                while (it.hasNext()) {
                    item = (FileItem) it.next();
                    String key = item.getFieldName();
                    String param = item.getString();
                    if (item.isFormField()) {
                        if(key.equals("description_post_grupo")){
                            description = param;
                        }
                    }else{
                        if (item.getSize() > 0) {
                            String nombreFile = item.getName();
                            
                            param = nombreFile;
                            String tipo = item.getContentType();
                            long size = item.getSize();
                            String extension = nombreFile.substring(nombreFile.lastIndexOf("."));
                            File archivo = new File(savePath, nombreFile);
                            item.write(archivo);
                            if (archivo.exists()) {
                                // Sustituir por un mensaje en la web
                                image = nombreFile;
                                System.out.println("GUARDADO " + archivo.getAbsolutePath() + "");
                                
                            }
                        }
                    }
                }

            } catch (FileUploadException ex) {
//               LogHandler.getLogger().error(ex.getMessage());
                throw new Exception(ex.getMessage());

            }
        }

//        try{
//            description = request.getParameter("description_post_grupo");
//            image += request.getParameter("image_post_grupo");
//        }catch(Exception ex){
//            RequestDispatcher rd;
//            rd = (RequestDispatcher)this.getServletContext().getRequestDispatcher("/error.jsp");
//            rd.forward(request, response);
//            return;
//        }
//        description = "Syriza apuesta por la medida que espanta a los inversores internacionales: auditar los 320.000 millones de euros de deuda, el 180% del PIB.";
//        image += "bg_4.jpg";
        
        // Creamos el Post
        Post post = new Post();
//        post.setIdPost(id_post);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(GrupoCrearPostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GrupoCrearPostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
