/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Post;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
/**
 *
 * @author fran
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {
    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    public List<Post> findByMuroIdUsuario (BigDecimal id_usuario) {
        Query q;
        List<Post> listaPost;        
        
        q = em.createQuery("SELECT p FROM Post p WHERE p.idUsuario.idUsuario=:ID ORDER BY p.idPost DESC");
        
        q.setParameter("ID", id_usuario);
        listaPost = q.getResultList();
        return listaPost;
        
    }
    
    public void deletePost(BigDecimal id_post){
        Query q;
        
        q=em.createQuery("DELETE FROM Post p WHERE p.idPost=:ID");
        q.setParameter("ID", id_post);
        
        q.executeUpdate();
    }
    
    public Map<String,String> obtenerDatosPost(HttpServletRequest request){
        
        Map<String,String> mapDatos = new HashMap();
        
        final String SAVE_DIR = "uploadImages";

        // Parametros del form
        String description = null;
        String url_image = null;
        String id_grupo = null;

        boolean isMultiPart;
        String filePath;
        int maxFileSize = 50 * 1024;
        int maxMemSize = 4 * 1024;
        File file = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        filePath = appPath + File.separator + "assets" + File.separator + "img" + File.separator + SAVE_DIR;
        String filePathWeb = "assets/img/" + SAVE_DIR + "/";
        // creates the save directory if it does not exists
        File fileSaveDir = new File(filePath);

        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        // Check that we have a file upload request
        isMultiPart = ServletFileUpload.isMultipartContent(request);
        if (isMultiPart) {

            // Parse the request
            List<FileItem> items = getMultipartItems(request);

            // Process the uploaded items
            Iterator<FileItem> iter = items.iterator();
            int offset = 0;
            int leidos = 0;
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {
                    // ProcessFormField
                    String name = item.getFieldName();
                    String value = item.getString();
                    if (name.equals("description_post_grupo")) {
                        description = value;
                    }else if(name.equals("id_grupo")){
                        id_grupo = value;
                    }
                } else {
                    // ProcessUploadedFile
                    try {
                        url_image = filePathWeb + item.getName();
                        // read this file into InputStream
                        inputStream = item.getInputStream();
                        // write the inputStream to a FileOutputStream
                        if (file == null) {
                            String fileDirUpload = filePath + File.separator + item.getName();
                            file = new File(fileDirUpload);
                            // crea el archivo en el sistema
                            file.createNewFile();
                            if (file.exists()) {
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
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        mapDatos.put("descripcion",description);
        mapDatos.put("imagen",url_image);
        mapDatos.put("id_grupo", id_grupo);
 
        return mapDatos;
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
                System.out.println("Error parsing multi-part form data: " + fuex.getMessage());
            }
        }
        return multipartItems;
    }

}
