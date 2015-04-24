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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.portlet.PortletFileUpload;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FileCleaningTracker;

/**
 *
 * @author fran
 */
@WebServlet(name = "GrupoCrearPostServlet", urlPatterns = {"/GrupoCrearPostServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
//@MultipartConfig
public class GrupoCrearPostServlet extends HttpServlet {

    private static void logException(FileUploadException fuex, HttpServletRequest request, String error_parsing_multipart_form_data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PostFacade postFacade;
    @EJB
    private GrupoFacade grupoFacade;

    private final static Logger LOGGER
            = Logger.getLogger(GrupoCrearPostServlet.class.getCanonicalName());

    /**
     * Name of the directory where uploaded files will be saved, relative to the
     * web application directory.
     */
    private static final String SAVE_DIR = "uploadImages";

    private String description = null;
    private String url_image = null;

    private boolean isMultiPart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file = null;
    private OutputStream outputStream = null;

    private boolean writeToFile = false;

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
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
//        String appPathWeb = request.getServletContext().getContextPath();
//        String appPathAux = appPath.substring(0, appPath.lastIndexOf("\\"));
        // constructs path of the directory to save uploaded file
//        filePath = appPathAux + File.separator + SAVE_DIR;
        filePath = appPath + File.separator + "assets" + File.separator  + "img" + File.separator + SAVE_DIR;
        String filePathWeb = "assets/img/" + SAVE_DIR + "/";
        // creates the save directory if it does not exists
        File fileSaveDir = new File(filePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        // Check that we have a file upload request
        isMultiPart = ServletFileUpload.isMultipartContent(request);
        if (isMultiPart) {
            // Create a factory for disk-based file items
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//
//            // Configure a repository (to ensure a secure temp location is used)
//            ServletContext servletContext = this.getServletConfig().getServletContext();
//            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
////            factory.setRepository(repository);
//
//            // Set factory constraints
//            factory.setSizeThreshold(maxMemSize);
//            factory.setRepository(repository); // yourTempDirectory
//
//            // Create a new file upload handler
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            // Set overall request size constraint
//            upload.setSizeMax(maxFileSize); // yourMaxRequestSize

            // Parse the request
//            List<FileItem> items = upload.parseRequest(request);
            List<FileItem> items = getMultipartItems(request);

            // Process the uploaded items
            Iterator<FileItem> iter = items.iterator();
            int offset = 0;
            int leidos = 0;
            while (iter.hasNext()) {
                FileItem item = iter.next();

                // Process a regular form field
                if (item.isFormField()) {
//                    processFormField(item);
                    String name = item.getFieldName();
                    String value = item.getString();
                    if (name.equals("description_post_grupo")) {
                        description = value;
                    }
                    // Process a file upload
                } else {
//                    processUploadedFile(item);
                    if (writeToFile) {
                        // No Vale peta
                        String fileName = item.getName();
                        File uploadedFile = new File(filePath, fileName);
                        url_image = uploadedFile.getAbsolutePath();
                        item.write(uploadedFile);
                    } else {
                        // Cambiar
//                        filePath = "assets" + File.separator + SAVE_DIR;
//                        url_image = filePath + "\\" + item.getName();
                        url_image = filePathWeb + item.getName();
                        InputStream uploadedStream = item.getInputStream();
                        InputStream inputStream = null;

                        try {
                            // read this file into InputStream
                            inputStream = uploadedStream;

                            // write the inputStream to a FileOutputStream
                            if (file == null) {
//                                file = new File(url_image);
                                String fileDirUpload = filePath + File.separator + item.getName();
                                file = new File(fileDirUpload);
                                // crea el archivo en el sistema
                                file.createNewFile();
                                if(file.exists()){
                                    outputStream = new FileOutputStream(file);
                                }
                            }

                            int read = 0;
                            byte[] bytes = new byte[1024];

                            while ((read = inputStream.read(bytes)) != -1) {
                                outputStream.write(bytes, offset, read);
                                leidos += read;
                            }
                            offset += leidos;
                            leidos = 0;
                            System.out.println("Done!");

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

//        try {
//
//            java.io.PrintWriter out = response.getWriter();
//
//            /*FileItemFactory es una interfaz para crear FileItem*/
//            FileItemFactory file_factory = new DiskFileItemFactory();
//
//            /*ServletFileUpload esta clase convierte los input file a FileItem*/
//            ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
//            /*sacando los FileItem del ServletFileUpload en una lista */
//            List items = servlet_up.parseRequest(request);
//            for (int i = 0; i < items.size(); i++) {
//                /*FileItem representa un archivo en memoria que puede ser pasado al disco duro*/
//                FileItem item = (FileItem) items.get(i);
//                /*item.isFormField() false=input file; true=text field*/
//                String key = item.getFieldName();
//                String value = item.getString();
//                if (item.isFormField()) {
//                    if (key.equals("description_post_grupo")) {
//                        description = value;
//                    }
//                } else {
//                    /*cual sera la ruta al archivo en el servidor*/
//                    url_image = filePath + "\\" + item.getName();
//                    File archivo_server = new File(url_image);
//                    /*y lo escribimos en el servido*/
//                    item.write(archivo_server);
//                    out.print("Nombre --> " + item.getName());
//                    out.print("<br> Tipo --> " + item.getContentType());
//                    out.print("<br> tamaño --> " + (item.getSize() / 1024) + "KB");
//                    out.print("<br>");
//                }
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(GrupoCrearPostServlet.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//        description = "Syriza apuesta por la medida que espanta a los inversores internacionales: auditar los 320.000 millones de euros de deuda, el 180% del PIB.";
//        image += "bg_4.jpg";
        // Creamos el Post
        Post post = new Post();
//        post.setIdPost(id_post);
        post.setIdUsuario(miembro);
        post.setIdGrupo(grupo);
        post.setDescripcion(description);
        post.setFecha(new Date());
        post.setImagen(url_image);

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

    /**
     * Provide the ability to cache multi-part items in a variable to save
     * re-parsing
     */
    public static List<FileItem> getMultipartItems(HttpServletRequest request) {
        List<FileItem> multipartItems = new LinkedList<FileItem>();
        if (FileUpload.isMultipartContent(new ServletRequestContext(request))) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                multipartItems = upload.parseRequest(request);
            } catch (FileUploadException fuex) {
                logException(fuex, request, "Error parsing multi-part form data");
            }
        }
        return multipartItems;
    }

    void depura(String cadena) {
        System.out.println("El error es " + cadena);
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
            Logger.getLogger(GrupoCrearPostServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GrupoCrearPostServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
