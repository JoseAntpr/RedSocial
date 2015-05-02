/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Grupo;
import ea.entity.Post;
import ea.entity.Usuario;
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
import javax.ejb.EJB;
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
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private GrupoFacade grupoFacade;
    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    public List<Post> findPostIdUsuarioOrder (BigDecimal id_usuario) {
        Query q;
        List<Post> listaPost;        
        
        q = em.createQuery("SELECT p FROM Post p WHERE p.idUsuario.idUsuario=:ID ORDER BY p.idPost DESC");
        
        q.setParameter("ID", id_usuario);
        listaPost = q.getResultList();
        return listaPost;
        
    }
    
    public List<Post> findPostIdUsuarioSeguidoresOrder (BigDecimal id_usuario) {
        Query q;
        List<Post> post;        
        
        q = em.createQuery("SELECT p FROM Post p, Usuario u ,IN (u.usuarioCollection) us WHERE u.idUsuario=:ID AND p.idUsuario.idUsuario=us.idUsuario ORDER BY p.idPost DESC");
        
        q.setParameter("ID", id_usuario);
        post = (List) q.getResultList();
        return post;
        
    }
    
    public void deletePost(BigDecimal id_post){
        Query q;
        
        q=em.createQuery("DELETE FROM Post p WHERE p.idPost=:ID");
        q.setParameter("ID", id_post);
        
        q.executeUpdate();
    }
    
    public void deletePostGrupo(BigDecimal id_post, Usuario usuario){
        // Busco el Post en la BD
        Post post = find(id_post);
        
        // Borro el post en el usuario sesion
        usuario.getPostCollection().remove(post);
        
        // Actualizo el usuario en la BD
        usuarioFacade.edit(usuario);
        
        // Borro el post en el grupo al que pertenece
        Grupo grupo = grupoFacade.find(post.getIdGrupo().getIdGrupo());
        grupo.getPostCollection().remove(post);
        
        // Actualizo el grupo en la BD
        grupoFacade.edit(grupo);
        
        // Borro el post de la BD
        remove(post);
        
    }
    
    public List getListaPostGrupo(BigDecimal idGrupo){
        return em.createQuery("SELECT p FROM Post p WHERE p.idGrupo.idGrupo = :ID ORDER BY p.idPost DESC")
                .setParameter("ID", idGrupo)
                .getResultList();
        
    }

}
