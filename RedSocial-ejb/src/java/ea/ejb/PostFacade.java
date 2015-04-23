/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Post;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
}
